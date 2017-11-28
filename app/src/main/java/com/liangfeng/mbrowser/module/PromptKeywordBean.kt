package com.liangfeng.mbrowser.module

import java.util.*

/**
 * Created by mzf on 2017/11/27.
 * Email:liangfeng093@gmail.com
 */
class PromptKeywordBean {
    var q: String? = ""
    var p: Boolean? = false
    var s: Array<String>? = null
    override fun toString(): String {
        return "PromptKeywordBean(q=$q, p=$p, s=${Arrays.toString(s)})"
    }


}