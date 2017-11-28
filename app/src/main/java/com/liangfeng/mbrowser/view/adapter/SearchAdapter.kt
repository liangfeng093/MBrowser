package com.liangfeng.mbrowser.view.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.view.fragment.SearchFragment

/**
 * Created by mzf on 2017/11/28.
 * Email:liangfeng093@gmail.com
 */
class SearchAdapter : BaseQuickAdapter<String, BaseViewHolder>, BaseQuickAdapter.OnItemClickListener {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        Log.e(TAG, "position:" + position)
        Log.e(TAG, "关键字:" + datas?.get(position))
        mFragment?.replaceFragment?.keyWords = datas?.get(position).toString()
        mFragment?.jumpTo(ReplaceFragmentEvent.WEB_FRAGMENT)
        mFragment?.keyWords?.clear()

    }

    var datas: MutableList<String>? = null
    var mAdapter: SearchAdapter? = null
    var mFragment: SearchFragment? = null

    val TAG = "SearchAdapter"

    constructor(mFragment: SearchFragment, layoutResId: Int, data: MutableList<String>?) : super(layoutResId, data) {
        Log.e(TAG, "data:" + data)
        datas = data
        mAdapter = this
        mAdapter?.setOnItemClickListener(this)
        this.mFragment = mFragment
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tvKeyword, item)
    }


}