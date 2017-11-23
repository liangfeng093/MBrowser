package com.liangfeng.mbrowser.base

import android.content.Context
import com.vondear.rxtools.RxTool
import org.litepal.LitePalApplication

/**
 * Created by mzf on 2017/11/22.
 * Email:liangfeng093@gmail.com
 */

class BaseApplication : LitePalApplication() {

    companion object{//相当于静态变量
        var contect : Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        contect = getContext()
//        RxTool.init(this)
    }
}
