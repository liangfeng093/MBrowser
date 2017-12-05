package com.liangfeng.mbrowser.contract

import com.liangfeng.mbrowser.base.BasePresenter
import com.liangfeng.mbrowser.base.BaseView
import com.liangfeng.mbrowser.presenter.BookmarkFragmentPresenter

/**
 * 契约类，用于管理接口
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
interface BrowsingHistoryContract {

    interface View : BaseView<BookmarkFragmentPresenter> {
        /**
         * 有数据，展示数据
         */
        fun showDate()

        /**
         * 没有数据,展示空视图
         */
        fun showEmpty()

        /**
         * 清空历史记录
         */
        fun clear()

        /**
         * 删除一条历史记录
         */
        fun delete()
    }

    interface Presenter : BasePresenter {
        fun getData()
        /**
         * 清空历史记录
         */
        fun clear()

        /**
         * 删除一条历史记录
         */
        fun delete(timeDetails: String)
    }
}