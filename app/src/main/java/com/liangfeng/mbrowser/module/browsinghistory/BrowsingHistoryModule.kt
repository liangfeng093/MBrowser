package com.liangfeng.mbrowser.module.browsinghistory

import android.content.ContentValues
import android.util.Log
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

    val mTAG = "BrowsingHistoryModule"
    fun deleteItem(item: BrowsingHistoryBean) {
        var result = DataSupport.deleteAll(BrowsingHistoryBean::class.java, "timeDetails=?", item?.timeDetails)
        var list = DataSupport.where("time=?", item?.time).find(BrowsingHistoryBean::class.java)
        var i = 0
        while (i < list?.size!!) {
            var bean = list?.get(i)
            var values = ContentValues()
            values?.put("position", i)
            DataSupport.update(BrowsingHistoryBean::class.java, values, bean?.id!!)
            i++
        }
//        Logger.e("deleteItem>>>result:" + result)
        Log.e(mTAG, "size:" + DataSupport.findAll(BrowsingHistoryBean::class.java).size)
    }

    fun find(): MutableList<BrowsingHistoryBean> {
        return DataSupport.findAll(BrowsingHistoryBean::class.java)
    }

    fun clear() {
        var result = DataSupport.deleteAll(BrowsingHistoryBean::class.java)
        Logger.e("clear>>>result:" + result)
    }

}