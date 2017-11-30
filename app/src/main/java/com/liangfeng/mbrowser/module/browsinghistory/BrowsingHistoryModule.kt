package com.liangfeng.mbrowser.module.browsinghistory

import com.liangfeng.mbrowser.base.BaseModule
import com.orhanobut.logger.Logger
import org.litepal.crud.DataSupport

/**
 * Created by mzf on 2017/11/30.
 * Email:liangfeng093@gmail.com
 */
class BrowsingHistoryModule {
    /* override fun onSuccess() {
     }

     override fun onFail() {
     }*/

    /* override fun loadNetworkData() {
     }

     override fun getLocalData() {

     }
 */

    fun getBrowsingHistory(callBack: BaseModule.GetLocalCallback<MutableList<BrowsingHistoryBean>>) {
        var historys: MutableList<BrowsingHistoryBean> = DataSupport.findAll(BrowsingHistoryBean::class.java)
        if (historys?.size > 0) {
            callBack.onSuccess(historys)
        } else {
            callBack.onFail()
        }
    }

}