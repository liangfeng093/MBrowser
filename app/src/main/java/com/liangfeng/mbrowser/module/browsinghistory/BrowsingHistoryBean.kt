package com.liangfeng.mbrowser.module.browsinghistory

import org.litepal.crud.DataSupport

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BrowsingHistoryBean : DataSupport() {
    var url: String? = ""
    var title: String? = ""
    var time: String? = ""

    override fun toString(): String {
        return "BrowsingHistoryBean(url=$url, title='$title')"
    }


}