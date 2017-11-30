package com.liangfeng.mbrowser.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.module.BrowsingHistoryBean
import com.liangfeng.mbrowser.view.adapter.BookmarkAdapter
import com.liangfeng.mbrowser.view.adapter.BookmarkPagerAdapter
import org.litepal.crud.DataSupport

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BookMarkFragment : BaseFragment() {
    override fun setPresenter() {
    }

    override fun setLayout(): Int {
        return R.layout.fragment_bookmark
    }

    var rvBookmark: RecyclerView? = null
    override fun initView() {
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
        var layoutManager = LinearLayoutManager(activity)
        rvBookmark?.layoutManager = layoutManager

        var historys: MutableList<BrowsingHistoryBean> = DataSupport.findAll(BrowsingHistoryBean::class.java)
        rvBookmark?.adapter = BookmarkAdapter(R.layout.item_bookmark, historys)

    }

    override fun setListener() {

    }
}