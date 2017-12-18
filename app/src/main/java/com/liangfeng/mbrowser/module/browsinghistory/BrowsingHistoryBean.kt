package com.liangfeng.mbrowser.module.browsinghistory

import org.litepal.crud.DataSupport

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BrowsingHistoryBean : DataSupport() {

    var id: Long = 0

    var url: String? = ""
    var title: String? = ""
    var time: String? = ""
    var timeDetails: String? = ""
//    var isSelect: Boolean = false
    var isRemove: Boolean = false
    var isShowStatus: Boolean = false

    var position: Int? = -1

    var groupId: String = "->time"
    var groupSize: Int = 0

    fun isFirstViewInGroup(): Boolean {//展示的shi反转的集合
        if (groupSize > 0) {
            return position == groupSize - 1
        } else {
            return position == 0
        }
    }

    fun isLastViewInGroup(): Boolean {//展示的shi反转的集合
        return position == 0
    }

    /*override fun toString(): String {
        return "BrowsingHistoryBean(title=$title, isSelect=$isSelect, isRemove=$isRemove, position=$position)"
    }
*/

}