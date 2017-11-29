package com.liangfeng.mbrowser.base

import android.content.Context
import android.net.http.HttpResponseCache.install
import com.liangfeng.mbrowser.network.RetrofitManager
import com.vondear.rxtools.RxTool
import me.yokeyword.fragmentation.BuildConfig
import me.yokeyword.fragmentation.Fragmentation
import org.litepal.LitePalApplication

/**
 * Created by mzf on 2017/11/22.
 * Email:liangfeng093@gmail.com
 */

class BaseApplication : LitePalApplication() {

    companion object {
        //相当于静态变量
        var contect: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        contect = getContext()
        RxTool.init(this)
        RetrofitManager.getInstance()
    }
}
