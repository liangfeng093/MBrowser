package com.liangfeng.mbrowser.view.fragment

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.R.id.rvBookmark
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.BookmarkEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
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
    var floatItem: TextView? = null
    var layoutManager: LinearLayoutManager? = null
    override fun initView() {

        var bean = BrowsingHistoryBean()
        bean?.title = "666"
        bean?.url = "666"


        BookmarkFragmentPresenter(this)
        rvBookmark = rootView?.findViewById<RecyclerView>(R.id.rvBookmark)
//        floatItem = rootView?.findViewById<TextView>(R.id.floatItem)
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
                        list?.get(position)?.groupId = list?.get(position)?.time.toString()
                    }
                } else {
                    list?.get(position)?.groupId = list?.get(position)?.time.toString()
                }
//                return list?.get(position)!!
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receive(event: BookmarkEvent) {
        list = event.list?.asReversed()
//        list = event.list
        floatItem?.setText(list?.get(0)?.time)
        var adapter = BookmarkAdapter(this, R.layout.item_bookmark, list)
        rvBookmark?.adapter = adapter
        adapter?.setOnItemClickListener(this)
    }

    var list: MutableList<BrowsingHistoryBean>? = null
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        var event = ReplaceFragmentEvent()
        event?.type = ReplaceFragmentEvent.WEB_FRAGMENT
        event?.url = list?.get(position)?.url.toString()
        EventBus.getDefault().post(event)
        activity?.onBackPressed()
    }

}