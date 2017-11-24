package com.liangfeng.mbrowser.network

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mzf on 2017/11/24.
 * Email:liangfeng093@gmail.com
 */
interface RetrofitService {

    //https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&tn=baidu&wd=%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E%E6%8E%A5%E5%8F%A3
    @GET("s?")
//    Observable search(@Query("wd") String wd)
    fun search(@Query("wd") wd: String)

}