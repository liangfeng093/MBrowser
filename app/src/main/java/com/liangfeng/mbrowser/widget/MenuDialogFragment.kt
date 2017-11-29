package com.liangfeng.mbrowser.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.liangfeng.mbrowser.R
import com.vondear.rxtools.RxImageTool

@SuppressLint("ValidFragment")
/**
 * Created by mzf on 2017/11/28.
 * Email:liangfeng093@gmail.com
 */
class MenuDialogFragment : DialogFragment {
    var context1: Context? = null

    @SuppressLint("ValidFragment")
    constructor(context1: Context?) : super() {
        this.context1 = context1
    }


    /* override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//去掉对话框标题
         var view = inflater?.inflate(R.layout.dialog_menu, container)
         var gridView = view?.findViewById<GridView>(R.id.gvMenu)
         gridView?.adapter = context1?.let { ItemAdapter(it) }
         return view
     }*/

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //// 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        var dialog = Dialog(context1, R.style.BottomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 设置Content前设定
        dialog.setContentView(R.layout.dialog_menu)
        dialog.setCanceledOnTouchOutside(true)// 外部点击

        var gridView = dialog?.findViewById<GridView>(R.id.gvMenu)
        gridView?.adapter = context1?.let { ItemAdapter(it) }

        // 设置宽度为屏宽, 靠近屏幕底部。
        var attr = dialog.window.attributes
        attr.gravity = Gravity.BOTTOM
        attr.width = WindowManager.LayoutParams.WRAP_CONTENT
        attr.verticalMargin = RxImageTool.px2dp(1f).toFloat()
        dialog.window.attributes = attr

        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    class ItemAdapter : BaseAdapter {

        val menuIcons = arrayOf(R.mipmap.menu_dark, R.mipmap.menu_dark, R.mipmap.menu_dark
                , R.mipmap.menu_dark, R.mipmap.menu_dark, R.mipmap.menu_dark
                , R.mipmap.menu_dark, R.mipmap.menu_dark, R.mipmap.menu_dark)

        val menuNames = arrayOf(R.string.menu_bookmark, R.string.menu_bookmark, R.string.menu_bookmark,
                R.string.menu_bookmark, R.string.menu_bookmark, R.string.menu_bookmark,
                R.string.menu_bookmark, R.string.menu_bookmark, R.string.menu_bookmark)

        var context: Context? = null

        constructor(context: Context) : super() {
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var convertView = LayoutInflater.from(context).inflate(R.layout.item_menu, null)
            convertView.findViewById<ImageView>(R.id.ivItemMenu).setImageResource(menuIcons[p0])
            convertView.findViewById<TextView>(R.id.tvItemMenu).setText(menuNames[p0])
            return convertView
        }

        override fun getItem(p0: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(p0: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            return menuIcons.size
        }

    }
}