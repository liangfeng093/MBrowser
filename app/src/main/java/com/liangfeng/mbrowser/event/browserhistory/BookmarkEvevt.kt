package com.liangfeng.mbrowser.event.browserhistory

import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkEvent {

    var isRefresh: Boolean? = false

    constructor(isRefresh: Boolean?, hasData: Boolean?, list: MutableList<BrowsingHistoryBean>?) {
        this.hasData = hasData
        this.list = list
        this.isRefresh = isRefresh
    }

    var hasData: Boolean? = false
    var list: MutableList<BrowsingHistoryBean>? = null
}