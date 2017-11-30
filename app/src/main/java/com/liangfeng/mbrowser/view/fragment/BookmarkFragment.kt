package com.liangfeng.mbrowser.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.BookmarkEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.presenter.BookmarkFragmentPresenter
import com.liangfeng.mbrowser.view.MainActivity
import com.liangfeng.mbrowser.view.adapter.BookmarkAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BookmarkFragment : BaseFragment(), BrowsingHistoryContract.View, BaseQuickAdapter.OnItemClickListener {


    var mPresenter: BookmarkFragmentPresenter? = null

    val mTAG = "BookmarkFragment"

    override fun setPresenter(presenter: BookmarkFragmentPresenter) {
        mPresenter = presenter
    }

    override fun showDate() {
        mPresenter?.getData()
    }

    override fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLayout(): Int {
        return R.layout.fragment_bookmark
    }

    var rvBookmark: RecyclerView? = null

    override fun initView() {
        BookmarkFragmentPresenter(this)
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
        var layoutManager = LinearLayoutManager(activity)
        rvBookmark?.layoutManager = layoutManager
        showDate()
    }

    override fun setListener() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receive(event: BookmarkEvent) {
        list = event.list
        var adapter = BookmarkAdapter(R.layout.item_bookmark, event.list)
        rvBookmark?.adapter = adapter
        adapter?.setOnItemClickListener(this)
    }

    var list: MutableList<BrowsingHistoryBean>? = null
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        var event = ReplaceFragmentEvent()
        event?.type = ReplaceFragmentEvent.WEB_FRAGMENT
        event?.url = list?.get(position)?.url.toString()
        EventBus.getDefault().post(event)
//        startActivity(Intent(activity, MainActivity::class.java))
        activity?.onBackPressed()
    }

}