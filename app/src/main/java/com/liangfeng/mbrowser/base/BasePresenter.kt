package com.liangfeng.mbrowser.base

/**
 * Created by mzf on 2017/11/21.
 * Email:liangfeng093@gmail.com
 */

interface BasePresenter {
    /**
     * 规定Presenter必须要实现start方法
     * 该方法的作用是Presenter开始获取数据并调用View的方法来刷新界面，其调用时机是在Fragment类的onResume方法中
     */
    fun start()
}
