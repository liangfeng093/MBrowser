package com.liangfeng.mbrowser.network

import android.util.Log
import com.liangfeng.mbrowser.module.PromptKeywordBean
import com.liangfeng.mbrowser.network.observer.NetworkObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by mzf on 2017/11/27.
 * Email:liangfeng093@gmail.com
 */
class RetrofitManager {
    //私有构造,单例模式
    private constructor() {
        ininRetrofit()
    }


    companion object {
        var retrofitManager: RetrofitManager? = null
        var retrofit: RetrofitService? = null

        fun getInstance(): RetrofitManager? {
            if (retrofitManager == null) {
                synchronized(RetrofitManager::class.java) {
                    //双锁
                    if (retrofitManager == null) {
                        retrofitManager = RetrofitManager()
                    }
                }
            }
            return retrofitManager
        }

        /**
         * 提示关键词
         */
//        fun promptKeyWords(keyWord: String, observable: Observer<PromptKeywordBean>) {
        fun promptKeyWords(keyWord: String, observable: NetworkObserver) {
            retrofit?.promptKeyWords(keyWord)
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observable)
        }
    }

    fun ininRetrofit() {
        //拦截器（打印网络请求log）
        var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)//添加拦截器
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(RetrofitService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())//配置gson转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//配置rxjava转换
                .client(okHttpClient)
                .build()
                .create(RetrofitService::class.java)//创建接口实例

    }


    /**
     * 日志拦截器
     */
    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            Log.e("HttpLogInfo", message)
        }
    }


}