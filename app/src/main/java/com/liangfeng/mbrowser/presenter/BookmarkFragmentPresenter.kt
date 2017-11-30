package com.liangfeng.mbrowser.presenter

import com.liangfeng.mbrowser.base.BaseModule
import com.liangfeng.mbrowser.contract.BrowsingHistoryContract
import com.liangfeng.mbrowser.event.BookmarkEvent
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryBean
import com.liangfeng.mbrowser.module.browsinghistory.BrowsingHistoryModule
import com.liangfeng.mbrowser.view.fragment.BookmarkFragment
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BookmarkFragmentPresenter : BrowsingHistoryContract.Presenter {

    private var module: BrowsingHistoryModule? = null
    private var view: BookmarkFragment? = null


    constructor(view: BookmarkFragment) {
        this.module = BrowsingHistoryModule()
        view?.setPresenter(this)
    }

    override fun getData() {
        Logger.e("getData")
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


    override fun start() {

    }


}