package com.liangfeng.mbrowser.network.observer

import android.util.Log
import com.liangfeng.mbrowser.module.PromptKeywordBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

/**
 * Created by mzf on 2017/11/28.
 * Email:liangfeng093@gmail.com
 */
open class NetworkObserver : Observer<ResponseBody> {
    override fun onNext(t: ResponseBody) {

    }

    constructor()


    override fun onComplete() {
        Log.e(TAG,"onComplete")
    }



    override fun onSubscribe(d: Disposable) {
        Log.e(TAG,"onSubscribe")
        d.isDisposed
    }

    val TAG = ""
    override fun onError(e: Throwable) {
        Log.e(TAG, "e:" + e.toString())
        Log.e(TAG, "onError" )
    }

}