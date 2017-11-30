package com.liangfeng.mbrowser.base

import android.app.Activity

/**
 * Created by mzf on 2017/11/21.
 * Email:liangfeng093@gmail.com
 */

interface BaseView<T> {
    /**
     * 规定View必须要实现setPresenter方法，则View中保持对Presenter的引用
     * 关联presenter
     */
    fun setPresenter(presenter: T)

}
