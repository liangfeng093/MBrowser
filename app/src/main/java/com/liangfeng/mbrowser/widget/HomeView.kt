package com.liangfeng.mbrowser.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.vondear.rxtools.RxTool

/**
 * Created by mzf on 2017/11/22.
 * Email:liangfeng093@gmail.com
 */

class HomeView : View {

    val TAG = "HomeView"

    var startX: Float = 0.0f
    var startY: Float = 0.0f
    var endX: Float = 0.0f
    var endY: Float = 0.0f
    var distanceX: Float = 0.0f
    var distanceY: Float = 0.0f
    var distanceZ: Float = 0.0f
    var angle: Float = 0.0f

    val paint: Paint = Paint()
    var rectTop: Float = 0f
    var rectBottom: Float = 0f
    var rectLeft: Float = 0f
    var rectRight: Float = 0f

    var canvas: Canvas? = null

    var mHomoViewClick: HomeViewClick? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {//绘制方法
        super.onDraw(canvas)
        RxTool.init(context)

        this.canvas = canvas
        //配置画笔属性
        paint.color = Color.parseColor("#808080")//画笔颜色
        paint.isAntiAlias = true //开启抗锯齿

        paint.style = Paint.Style.STROKE//画线模式
        paint.strokeWidth = 3f

        rectTop = ((height / 2 - 180)).toFloat()
        rectBottom = ((height / 2 - 50)).toFloat()
        rectLeft = ((width / 2 - 350)).toFloat()
        rectRight = ((width / 2 + 350)).toFloat()

        //绘制圆角矩形
        canvas?.drawRoundRect(rectLeft, rectTop + distanceY / 5, rectRight, rectBottom + distanceY / 5, 150F, 150f, paint)

        paint.style = Paint.Style.FILL//画线模式
        paint.alpha = 0

        paint.color = Color.parseColor("#000000")//画笔颜色
        canvas?.drawCircle(rectLeft + 60, rectBottom + 200 + distanceY / 5, 50f, paint)

        paint.color = Color.parseColor("#2196f3")//画笔颜色
        canvas?.drawCircle(rectLeft + 260, rectBottom + 200 + distanceY / 5, 50f, paint)

        paint.color = Color.parseColor("#4CAF50")//画笔颜色
        canvas?.drawCircle(rectLeft + 460, rectBottom + 200 + distanceY / 5, 50f, paint)

        paint.color = Color.parseColor("#ffc107")//画笔颜色
        canvas?.drawCircle(rectLeft + 660, rectBottom + 200 + distanceY / 5, 50f, paint)


    }


    @SuppressLint("WrongCall")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
        //手指按下
            MotionEvent.ACTION_DOWN -> {
                startX = event.rawX
                startY = event.rawY
            }

            MotionEvent.ACTION_MOVE -> {

                endX = event.rawX
                endY = event.rawY

                distanceX = Math.abs(endX - startX)
                distanceY = Math.abs(endY - startY)

                distanceZ = Math.sqrt(((distanceX * distanceX) + (distanceY * distanceY)).toDouble()).toFloat()

                angle = distanceX / distanceZ

                if (angle < 0.5) {
                    Log.e(TAG, "上下移动")
                    if (canvas == null) {
                        canvas = Canvas()
                    }
                    if (distanceY > 0) {
                        this.invalidate()//重新绘制
                    }
                } else {
                    Log.e(TAG, "左右移动")
                }
            }

        //手指抬起
            MotionEvent.ACTION_UP -> {
                distanceY = 0f
                this.invalidate()//重新绘制
                var x = event.getX()
                var y = event.getY()
                if (x > rectLeft && x < rectRight && y < rectBottom && y > rectTop) {
                    mHomoViewClick?.onViewCilik()
                }else{

                }
            }
        }
        return true//自己处理!!!!!!!
    }


    //设置自定义的点击事件监听
    fun setOnHomeViewClick(homoViewClick: HomeViewClick) {
        mHomoViewClick = homoViewClick
    }

    //自定义点击事件接口
    interface HomeViewClick {
        fun onViewCilik()
    }
}
