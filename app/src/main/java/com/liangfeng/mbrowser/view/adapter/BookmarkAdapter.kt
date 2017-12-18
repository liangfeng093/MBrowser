package com.liangfeng.mbrowser.view.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.event.browserhistory.LongClickEvent
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.view.fragment.BrowsingHistoryFragment
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkAdapter : BaseQuickAdapter<BrowsingHistoryBean, BaseViewHolder>, BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemClickListener {

    val mTAG = "BookmarkAdapter"
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        var bean = list?.get(position)

        if (isSelect) {

            if (!bean?.isRemove!!) {
                view?.findViewById<ImageView>(R.id.ivSelectItemBookmark)?.setImageResource(R.mipmap.bookmark_select_yes)
                bean?.isRemove = true
            } else {
                view?.findViewById<ImageView>(R.id.ivSelectItemBookmark)?.setImageResource(R.mipmap.bookmark_select_no)
                bean?.isRemove = false
            }
        } else {
            var event = ReplaceFragmentEvent()
            event?.type = ReplaceFragmentEvent.WEB_FRAGMENT
            event?.url = list?.get(position)?.url.toString()
            EventBus.getDefault().post(event)
            mFragment?.activity?.onBackPressed()
        }
        Log.e(mTAG, "onItemClick》》》bean:" + bean)
    }

    var isSelect: Boolean = false
    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {
        isSelect = true
        list?.forEach { it?.isShowStatus = true }
        notifyDataSetChanged()
        var event = LongClickEvent()
//        event?.isShow = true
        EventBus.getDefault().post(event)
        return true
    }


    companion object {
        val NEED_FLOAT = 1
        val NO_FLOAT = 2
    }

    var list: MutableList<BrowsingHistoryBean>? = null
    var mFragment: BrowsingHistoryFragment? = null

    constructor(mFragment: BrowsingHistoryFragment, layoutResId: Int, data: MutableList<BrowsingHistoryBean>?) : super(layoutResId, data) {
        list = data
        this.mFragment = mFragment
        setOnItemClickListener(this)
        setOnItemLongClickListener(this)
    }



    override fun convert(helper: BaseViewHolder?, item: BrowsingHistoryBean?) {
        helper?.setText(R.id.tvItemBookmark, item?.title)
//        Log.e(mTAG, "convert》》》bean:" + item?.toString())
        if (item?.isShowStatus!!) {//显示选中按钮
            helper?.setVisible(R.id.ivSelectItemBookmark, true)
            helper?.getView<ImageView>(R.id.ivSelectItemBookmark)?.setImageResource(R.mipmap.bookmark_select_no)
        } else {//隐藏选中按钮
            helper?.getView<ImageView>(R.id.ivSelectItemBookmark)?.visibility = View.GONE
        }
    }
}