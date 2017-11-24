package com.liangfeng.mbrowser.view.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.WebEvent
import org.greenrobot.eventbus.Subscribe

@SuppressLint("ValidFragment")
/**
 * Created by mzf on 2017/11/24.
 * Email:liangfeng093@gmail.com
 */
class WebFragment : BaseFragment {



    /***************** View ******************/
    var webView: WebView? = null

    var url: String = ""

    constructor(url: String) : super() {
        this.url = url
    }

    override fun setPresenter() {

    }

    override fun setLayout(): Int {
        return R.layout.fragment_web
    }

    override fun initView() {
        webView = rootView?.findViewById<WebView>(R.id.webView)
        webView?.webViewClient = WebViewClient()

    }

    override fun setListener() {

    }

    val mTAG: String = "WebFragment"

    @Subscribe
    fun showWeb(webEvent: WebEvent) {
        Log.e(mTAG, "121")
        Log.e(mTAG, "url:" + webEvent.url)
        url = webEvent.url
    }

    override fun onResume() {
        super.onResume()
        Log.e(mTAG, "onResume")
        Log.e(mTAG, "url:" + url)

        webView?.loadUrl(url)//确保view绘制完毕
    }
}