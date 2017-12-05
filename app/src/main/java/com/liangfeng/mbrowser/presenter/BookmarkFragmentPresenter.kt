package com.liangfeng.mbrowser.presenter

import com.liangfeng.mbrowser.base.BaseModule
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.browserhistory.BookmarkEvent
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryModule
import com.liangfeng.mbrowser.view.fragment.BrowsingHistoryFragment
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkFragmentPresenter : BrowsingHistoryContract.Presenter {

    private var module: BrowsingHistoryModule? = null
    private var view: BrowsingHistoryFragment? = null


    constructor(view: BrowsingHistoryFragment) {
        this.module = BrowsingHistoryModule()
        view?.setPresenter(this)
    }

    override fun getData() {
        module?.getBrowsingHistory(object : BaseModule.GetLocalCallback<MutableList<BrowsingHistoryBean>> {
            override fun onSuccess(t: MutableList<BrowsingHistoryBean>) {
                var event = BookmarkEvent(true, t)
                EventBus.getDefault().post(event)
            }

            override fun onFail() {
                var event = BookmarkEvent(false, null)
                EventBus.getDefault().post(event)
            }
        })
    }
    override fun clear() {

    }

    override fun delete(timeDetails:String) {
        module?.deleteItem(timeDetails)
    }


    override fun start() {

    }


}