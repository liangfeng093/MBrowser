package com.liangfeng.mbrowser.widget

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.*
import android.widget.*
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.view.activity.HistoryActivity
import com.vondear.rxtools.RxImageTool

@SuppressLint("ValidFragment")
/**
 * Created by mzf on 2017/11/28.
 * Email:liangfeng093@gmail.com
 */
class MenuDialogFragment : DialogFragment {
    var context1: Context? = null
    val TAG = "MenuDialogFragment"

    @SuppressLint("ValidFragment")
    constructor(context1: Context?) : super() {
        this.context1 = context1
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        var dialog = Dialog(context1, R.style.BottomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 设置Content前设定
        dialog.setContentView(R.layout.dialog_menu)
        dialog.setCanceledOnTouchOutside(true)// 外部点击

        var gridView = dialog?.findViewById<GridView>(R.id.gvMenu)
        gridView?.adapter = context1?.let { ItemAdapter(it) }
        gridView?.setOnItemClickListener { adapterView, view, i, l ->
            Log.e(TAG, "POSITION:" + i)
            var intent: Intent
            when (i) {
                0 -> {//书签历史
                    intent = Intent(activity, HistoryActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
                5 -> {
                }
                6 -> {
                }
                7 -> {
                }
                8 -> {
                }
                9 -> {
                }
            }
        }
        // 设置宽度为屏宽, 靠近屏幕底部。
        var attr = dialog.window.attributes
        attr.gravity = Gravity.BOTTOM
        attr.width = WindowManager.LayoutParams.WRAP_CONTENT
        attr.windowAnimations = R.style.dialogAnim
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
            return p0.toLong()
        }

        override fun getCount(): Int {
            return menuIcons.size
        }

    }
}