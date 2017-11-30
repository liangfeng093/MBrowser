package com.liangfeng.mbrowser.event

import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkEvent {

    constructor(hasData: Boolean?, list: MutableList<BrowsingHistoryBean>?) {
        this.hasData = hasData
        this.list = list
    }

    var hasData: Boolean? = false
    var list: MutableList<BrowsingHistoryBean>? = null
}