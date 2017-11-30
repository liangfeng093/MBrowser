package com.liangfeng.mbrowser.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.BookmarkEvent
import com.liangfeng.mbrowser.presenter.BookmarkFragmentPresenter
import com.liangfeng.mbrowser.view.adapter.BookmarkAdapter
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BookmarkFragment : BaseFragment(), BrowsingHistoryContract.View {

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
        EventBus.getDefault().register(this)
        BookmarkFragmentPresenter(this)
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
        var layoutManager = LinearLayoutManager(activity)
        rvBookmark?.layoutManager = layoutManager

        showDate()
//        var historys: MutableList<BrowsingHistoryBean> = DataSupport.findAll(BrowsingHistoryBean::class.java)
//        rvBookmark?.adapter = BookmarkAdapter(R.layout.item_bookmark, historys)

    }

    override fun setListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receive(event: BookmarkEvent) {
        rvBookmark?.adapter = BookmarkAdapter(R.layout.item_bookmark, event.list)
    }

}