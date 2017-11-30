package com.liangfeng.mbrowser.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkAdapter : BaseQuickAdapter<BrowsingHistoryBean, BaseViewHolder> {

    var list: MutableList<BrowsingHistoryBean>? = null

    constructor(layoutResId: Int, data: MutableList<BrowsingHistoryBean>?) : super(layoutResId, data) {
        list = data
    }

    override fun convert(helper: BaseViewHolder?, item: BrowsingHistoryBean?) {
        helper?.setText(R.id.tvItemBookmark, item?.title)
    }

}