package com.liangfeng.mbrowser.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.view.fragment.BookmarkFragment
import com.orhanobut.logger.Logger

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkAdapter : BaseQuickAdapter<BrowsingHistoryBean, BaseViewHolder> {

    companion object {
        val NEED_FLOAT = 1
        val NO_FLOAT = 2
    }

    var list: MutableList<BrowsingHistoryBean>? = null
    var mFragment: BookmarkFragment? = null

    constructor(mFragment: BookmarkFragment, layoutResId: Int, data: MutableList<BrowsingHistoryBean>?) : super(layoutResId, data) {
        list = data
        this.mFragment = mFragment
    }

    override fun convert(helper: BaseViewHolder?, item: BrowsingHistoryBean?) {
        if (helper?.position!! > 0) {
            var bean = list?.get(helper?.position - 1)
            var time1 = bean?.time
            var time2 = item?.time
            if (time1.equals(time2)) {//同时间段的浏览记录
                helper?.setVisible(R.id.timeFlag, false)
                helper?.setTag(R.id.rootContainer, NEED_FLOAT)
            } else {
                helper?.setTag(R.id.rootContainer, NO_FLOAT)
                helper?.setVisible(R.id.timeFlag, true)
                helper?.setText(R.id.tvTime, item?.time)
            }
        }
        helper?.setText(R.id.tvItemBookmark, item?.title)
    }

}