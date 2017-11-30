package com.liangfeng.mbrowser.base

import android.content.Context
import com.liangfeng.mbrowser.network.RetrofitManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxTool
import org.litepal.LitePal
import org.litepal.LitePalApplication
import org.litepal.util.Const

/**
 * Created by mzf on 2017/11/22.
 * Email:liangfeng093@gmail.com
 */

class BaseApplication : LitePalApplication() {

    companion object {
        //相当于静态变量
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = getContext()
        RxTool.init(this)
        LitePal.initialize(context)
        RetrofitManager.getInstance()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
