package com.liangfeng.mbrowser.event

/**
 * Created by mzf on 2017/11/23.
 * Email:liangfeng093@gmail.com
 */
class ReplaceFragmentEvent {

    companion object {
        val HOME_FRAGMENT = 1
        val SEARCH_FRAGMENT = 2
        val WEB_FRAGMENT = 3
    }

    var type: Int = 0
    var keyWords: String = ""
    var url: String = ""

    constructor()
}