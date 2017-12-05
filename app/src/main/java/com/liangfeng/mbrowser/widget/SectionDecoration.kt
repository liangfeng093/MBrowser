package com.liangfeng.mbrowser.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.text.TextPaint
import android.view.View
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.orhanobut.logger.Logger

/**
 * Created by mzf on 2017/12/1.
 * Email:liangfeng093@gmail.com
 */
class SectionDecoration : RecyclerView.ItemDecoration {


    /**
     *数据分组回调
     */
    interface GroupInfoCallback {
        fun getGroupInfo(position: Int): BrowsingHistoryBean
    }

    var mCallBack: GroupInfoCallback? = null
    var mHeaderHeight: Float? = null
    var mDividerHeight: Int? = null

    //title距离左边的距离
    var mTextOffset: Float? = 10f
    var mTextSize: Float? = null
    var mFontMetrics: Paint.FontMetrics? = null
    var mPaint: Paint? = Paint()
    var textPaint: TextPaint? = TextPaint()

    constructor(context: Context, mCallBack: GroupInfoCallback?) : super() {
        this.mCallBack = mCallBack
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_divider_height);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height).toFloat();
        mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.sp_12).toFloat();

        mHeaderHeight = Math.max(mHeaderHeight!!, mTextSize!!)

        textPaint = TextPaint()
        textPaint?.setColor(Color.BLACK)
        textPaint?.setTextSize(mTextSize!!)
        mFontMetrics = textPaint?.getFontMetrics()

        mPaint = Paint()
        mPaint?.setAntiAlias(true)
        mPaint?.setColor(Color.YELLOW)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        //获取itemView在适配器中的位置
        var position: Int? = parent?.getChildAdapterPosition(view)
        //判断item是不是分组中的第一条
        if (mCallBack != null) {
            var info = position?.let { mCallBack?.getGroupInfo(it) }
            var isFirst: Boolean? = info?.isFirstViewInGroup()
            if (isFirst!!) {
                //撑起一个header的高度
                outRect?.top = mHeaderHeight?.toInt()
            } else {//分割线高度
                outRect?.top = mDividerHeight
            }
        }
    }

    //绘制屏幕上显示的itemView时调用
    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        //遍历所有的ItemView
        var childCount = parent?.childCount
        var account = 0
        while (account < childCount!!) {
            //获取View
            var view = parent?.getChildAt(account)
            //获取view在适配器中的位置
            var index = parent?.getChildAdapterPosition(view)
            if (mCallBack != null) {
                var info = index?.let { mCallBack?.getGroupInfo(it) }
                var isFirst: Boolean? = info?.isFirstViewInGroup()
                var left = view?.paddingLeft
                var right = view?.width?.minus(view?.paddingRight)

                if (account != 0) {

                    if (isFirst!!) {//组内第一条数据
                        //itemView高度-header高度（向上）
                        var top = mHeaderHeight?.let { view?.top?.minus(it) }
                        var bottom = view?.top
                        drawHeader(c, top, right, bottom, left, info)
                    }

                } else {//屏幕第一个可见itemView
                    //RecyclerView的顶部位置
                    var top = parent?.paddingTop
                    if (info?.isLastViewInGroup()!!) {
                        var groupLastTop = mHeaderHeight?.let { view?.bottom?.minus(it) }//组内最后一条的高度(位置)
                        if (groupLastTop!! < top!!) {//组内最后一条的位置小于parent高度的位置（屏幕之外）
                            top = groupLastTop?.toInt()
                        }
                    }
                    //顶部位置+header高度（向下）
                    var bottom = mHeaderHeight?.let { top?.plus(it) }
                    drawHeader(c, top?.toFloat(), right, bottom?.toInt(), left, info)
                }
            }
            account++
        }
    }

    //绘制header
    private fun drawHeader(c: Canvas?, top: Float?, right: Int?, bottom: Int?, left: Int?, info: BrowsingHistoryBean?) {
        c?.drawRect(0f, top?.toFloat()!!, right?.toFloat()!!, bottom?.toFloat()!!, mPaint)

        var titleX: Float = left?.toFloat()!!
        var titleY: Float = bottom?.minus(mFontMetrics?.descent!!)!!

        c?.drawText(info?.time, titleX, titleY, textPaint)
    }
}