package com.liangfeng.mbrowser.view.fragment

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.browserhistory.BookmarkEvent
import com.liangfeng.mbrowser.event.browserhistory.ClearHistoryEvent
import com.liangfeng.mbrowser.event.browserhistory.DeleteHistoryEvent
import com.liangfeng.mbrowser.event.browserhistory.HistoryFinishEvent
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.presenter.BookmarkFragmentPresenter
import com.liangfeng.mbrowser.view.adapter.BookmarkAdapter
import com.liangfeng.mbrowser.widget.SectionDecoration
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.litepal.crud.DataSupport

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class BrowsingHistoryFragment : BaseFragment(), BrowsingHistoryContract.View {


    var mPresenter: BookmarkFragmentPresenter? = null

    val mTAG = "BrowsingHistoryFragment"

    override fun setPresenter(presenter: BookmarkFragmentPresenter) {
        mPresenter = presenter
    }

    override fun showDate() {
        mPresenter?.getData()
    }

    override fun showEmpty() {
    }

    override fun clear() {

    }

    override fun delete() {
    }

    override fun setLayout(): Int {
        return R.layout.fragment_bookmark
    }

    var rvBookmark: RecyclerView? = null
    var floatItem: TextView? = null
    var layoutManager: LinearLayoutManager? = null
    override fun initView() {
        BookmarkFragmentPresenter(this)
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
        layoutManager = LinearLayoutManager(activity)
        rvBookmark?.layoutManager = layoutManager
        showDate()
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun setListener() {
        rvBookmark?.addItemDecoration(SectionDecoration(activity, object : SectionDecoration.GroupInfoCallback {
            override fun getGroupInfo(position: Int): BrowsingHistoryBean {
                //数据分组
                var bean = list?.get(position)
                if (position > 0) {
                    var time = bean?.time
                    var beforeBean = list?.get(position - 1)
                    var beforeTime = beforeBean?.time
                    var isSame = time?.equals(beforeTime)
                    var count = DataSupport.where("time=?", bean?.time).count("BrowsingHistoryBean")
                    bean?.groupSize = count
                    if (isSame!!) {
                        bean?.groupId = bean?.time.toString()
                    }
                } else {
                    list?.get(position)?.groupId = list?.get(position)?.time.toString()
                }
                return bean!!
            }
        }))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    var adapter: BookmarkAdapter? = null

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receive(event: BookmarkEvent) {
        if (event?.hasData!!) {
            if (list == null && adapter == null) {
                list = event.list?.asReversed()
                adapter = BookmarkAdapter(this, R.layout.item_bookmark, list)
                rvBookmark?.adapter = adapter
            } else if (event?.isRefresh!!) {
                adapter?.isSelect = true
                list?.clear()
                event.list?.asReversed()?.let { list?.addAll(it) }
                list?.forEach {
                    it?.isShowStatus = true
                }
                list?.forEach {
                    Logger.e("it:" + it)
                }
                adapter?.notifyDataSetChanged()

            }
        }
    }

    var list: MutableList<BrowsingHistoryBean>? = null

    @Subscribe
    fun clear(event: ClearHistoryEvent) {
        list?.clear()
        mPresenter?.clear()
        adapter?.notifyDataSetChanged()
    }

    @Subscribe
    fun delete(event: DeleteHistoryEvent) {
        list?.forEach {
            if (it?.isRemove) {
                mPresenter?.delete(it)
            }
        }
        showDate()
    }

    @Subscribe
    fun finish(event: HistoryFinishEvent) {
        list?.forEach {
            it?.isShowStatus = false
        }
        adapter?.isSelect = false
        adapter?.notifyDataSetChanged()
    }
}