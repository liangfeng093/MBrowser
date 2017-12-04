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
        helper?.setText(R.id.tvItemBookmark, item?.title)
    }

}