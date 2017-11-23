package com.liangfeng.mbrowser.base

import android.app.Activity

/**
 * Created by mzf on 2017/11/21.
 * Email:liangfeng093@gmail.com
 */

interface BaseView {
    /**
     * 关联presenter
     */
    fun setPresenter()

    /**
     * 设置布局
     */
    fun setLayout(): Int

    /**
     * 初始化控件
     */
    fun initView()
    /**
     * 设置监听
     */
    fun setListener()
    /**
     * 设置父activity
     */
//    fun setActivity():Activity
}
