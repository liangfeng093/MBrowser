package com.liangfeng.mbrowser.view

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.BackEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.event.WebEvent
import com.liangfeng.mbrowser.network.Url
import com.liangfeng.mbrowser.view.activity.BaseActivity
import com.liangfeng.mbrowser.view.fragment.HomeFragment
import com.liangfeng.mbrowser.view.fragment.SearchFragment
import com.liangfeng.mbrowser.view.fragment.WebFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {
    override fun initView() {
    }

    override fun setListener() {
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun setPresenter() {
    }

    var homeFragment: HomeFragment? = null
    var searchFragment: SearchFragment? = null
    var webFragment: WebFragment? = null

    var webEvent: WebEvent? = WebEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contains, homeFragment)
                .commit()


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    val MYTAG = "MainActivity"
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onHitToolBar1(eventBack: BackEvent) {
        if (eventBack.isShowToolBar!!) {
            tools.visibility = View.GONE
        } else {
            tools.visibility = View.VISIBLE
        }
    }

    val mTAG: String = "MainActivity"
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onHitToolBar1(replaceFragment: ReplaceFragmentEvent) {
        Log.e(mTAG, "type:" + replaceFragment.type)
        when (replaceFragment.type) {

            ReplaceFragmentEvent.HOME_FRAGMENT -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, homeFragment)
                        .commit()
                tools.visibility = View.VISIBLE
            }

            ReplaceFragmentEvent.SEARCH_FRAGMENT -> {

                tools.visibility = View.INVISIBLE
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, searchFragment)
                        .commit()
            }

            ReplaceFragmentEvent.WEB_FRAGMENT -> {
                tools.visibility = View.VISIBLE
                var url = Url.BAI_DU + "wd="+replaceFragment.keyWords//拼接关键字
                webFragment = WebFragment(url)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, webFragment)
                        .commit()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
