package com.liangfeng.mbrowser.view

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.EventBack
import com.liangfeng.mbrowser.event.EventReplaceFragment
import com.liangfeng.mbrowser.view.activity.BaseActivity
import com.liangfeng.mbrowser.view.fragment.HomeFragment
import com.liangfeng.mbrowser.view.fragment.SearchFragment
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
    fun onHitToolBar1(eventBack: EventBack) {
        if (eventBack.isShowToolBar!!) {
            tools.visibility = View.GONE
        } else {
            tools.visibility = View.VISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onHitToolBar1(replaceFragment: EventReplaceFragment) {

        when (replaceFragment.type) {

            EventReplaceFragment.HOME_FRAGMENT -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, homeFragment)
                        .commit()
                tools.visibility = View.VISIBLE
            }

            EventReplaceFragment.SEARCH_FRAGMENT -> {

                tools.visibility = View.INVISIBLE
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, searchFragment)
                        .commit()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
