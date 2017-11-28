package com.liangfeng.mbrowser.view.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.mbrowser.R

/**
 * Created by mzf on 2017/11/28.
 * Email:liangfeng093@gmail.com
 */
class SearchAdapter : BaseQuickAdapter<String, BaseViewHolder> {

    var datas: MutableList<String>? = null

    val TAG = "SearchAdapter"

    constructor(layoutResId: Int, data: MutableList<String>?) : super(layoutResId, data) {
        Log.e(TAG, "data:" + data)
        datas = data
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tvKeyword, item)
    }

}