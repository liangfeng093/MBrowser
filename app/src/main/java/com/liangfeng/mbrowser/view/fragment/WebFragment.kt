package com.liangfeng.mbrowser.view.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.R.id.webView
import com.liangfeng.mbrowser.event.WebEvent
import com.liangfeng.mbrowser.view.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
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
//        webView?.webViewClient = WebViewClient()
        val settings = webView?.settings
        settings?.javaScriptEnabled = true//支持JS交互
        settings?.useWideViewPort = true//将图片调整到适合webview的大小
        settings?.loadWithOverviewMode = true// 缩放至屏幕的大小
        settings?.setSupportZoom(true)//支持缩放，默认为true。是下面那个的前提。
        settings?.displayZoomControls = false//隐藏原生的缩放控件

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.e(TAG, "onPageStarted")
                (activity as MainActivity).progress?.setBackgroundColor(Color.WHITE)
                (activity as MainActivity).progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.e(TAG, "onPageFinished")
                (activity as MainActivity).progress?.visibility = View.INVISIBLE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.e(TAG, "shouldOverrideUrlLoading")
                view?.loadUrl(url)
                return true//不打开系统浏览器
            }
        }
        webView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.e(TAG, "newProgress:" + newProgress)
                (activity as MainActivity).progress?.progress = newProgress.toFloat()-2
                (activity as MainActivity).progress?.secondaryProgress = newProgress.toFloat()
//                (activity as MainActivity).progress?.max = newProgress.toFloat()
//                Log.e(TAG, "progress:" + (activity as MainActivity).progress)
//                Log.e(TAG, "progress:" + (activity as MainActivity).mProgress?.progress)
//                (activity as MainActivity).mProgress?.progress = newProgress.toFloat()
            }
        }
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