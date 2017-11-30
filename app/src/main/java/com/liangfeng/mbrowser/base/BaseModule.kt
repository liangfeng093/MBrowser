package com.liangfeng.mbrowser.base

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
interface BaseModule {

    /**
     * 加载网络数据
     */
    interface LoadNetworkCallback<T> {
        fun onSuccess(t: T)
        fun onFail()
    }

    /**
     * 获取本地数据(数据库)
     */
    interface GetLocalCallback<T> {
        fun onSuccess(t: T)
        fun onFail()
    }

    /* fun loadNetworkData()

     fun getLocalData()
 */
}