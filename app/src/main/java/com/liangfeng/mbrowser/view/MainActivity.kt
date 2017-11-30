package com.liangfeng.mbrowser.view

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.widget.Toast
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.BackEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.event.WebEvent
import com.liangfeng.mbrowser.network.Url
import com.liangfeng.mbrowser.view.activity.BaseActivity
import com.liangfeng.mbrowser.view.fragment.HomeFragment
import com.liangfeng.mbrowser.view.fragment.SearchFragment
import com.liangfeng.mbrowser.view.fragment.WebFragment
import com.liangfeng.mbrowser.widget.MenuDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {

    var progress: RoundCornerProgressBar? = null
    var replaceFragment: ReplaceFragmentEvent? = ReplaceFragmentEvent()
    override fun initView() {
        this.progress = mProgress
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {

        /*ivSearchBar?.setOnClickListener {

            ivSearchBar?.animate()
                    ?.translationY(50f)
                    ?.alpha(0f)
                    ?.setDuration(300)?.withEndAction {

                replaceFragment?.type = ReplaceFragmentEvent.SEARCH_FRAGMENT
                EventBus.getDefault().post(replaceFragment)
            }
        }*/
        windows.setOnClickListener {

            Log.e(mTAG, "windows")
        }

        up.setOnClickListener {
            Log.e(mTAG, "up")

        }

        menu.setOnClickListener {
            Log.e(mTAG, "弹出menu")

            var dialogMenu = MenuDialogFragment(MainActivity@ this)
            dialogMenu.show(supportFragmentManager, "MenuDialogFragment")
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
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
    fun onHitToolBar1(event: ReplaceFragmentEvent) {
        Log.e(mTAG, "type:" + event.type)
        when (event.type) {

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
                var url: String = ""
                if (event.url.isEmpty()) {
                    url = Url.BAI_DU + "wd=" + event.keyWords//拼接关键字

                } else {
                    url = event.url
                }
                webFragment = WebFragment(url)
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contains, webFragment)
                        .commitAllowingStateLoss()//允许状态值丢失
//                        .commit()
            }
        }
    }

    var secondTime = 0L
    var firstTime = 0L
    override fun onBackPressed() {
        secondTime = System.currentTimeMillis()
        if (secondTime - firstTime > 2000) {
            Toast.makeText(MainActivity@ this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
