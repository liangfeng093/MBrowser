package com.liangfeng.mbrowser.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.liangfeng.mbrowser.R

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BookMarkFragment : BaseFragment() {
    override fun setPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLayout(): Int {
        return R.layout.fragment_bookmark
    }

    var rvBookmark: RecyclerView? = null
    override fun initView() {
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
        var layoutManager = LinearLayoutManager(activity)
        rvBookmark?.layoutManager = layoutManager

    }

    override fun setListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}