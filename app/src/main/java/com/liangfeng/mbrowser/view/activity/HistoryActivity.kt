package com.liangfeng.mbrowser.view.activity

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.browserhistory.ClearHistoryEvent
import com.liangfeng.mbrowser.event.browserhistory.DeleteHistoryEvent
import com.liangfeng.mbrowser.event.browserhistory.HistoryFinishEvent
import com.liangfeng.mbrowser.event.browserhistory.LongClickEvent
import com.liangfeng.mbrowser.view.adapter.BookmarkPagerAdapter
import com.liangfeng.mbrowser.view.fragment.BrowsingHistoryFragment
import kotlinx.android.synthetic.main.activity_history.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.reflect.Field

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class HistoryActivity : BaseActivity() {

    val mTAG = "HistoryActivity"

    override fun setLayout(): Int {
        return R.layout.activity_history
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        var fragments = listOf<Fragment>(BrowsingHistoryFragment(), BrowsingHistoryFragment())
        vpHistory.adapter = BookmarkPagerAdapter(supportFragmentManager, fragments)

        /* tabLayout.post {
             setIndicator(tabLayout, 35, 35)
         }*/
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.setupWithViewPager(vpHistory)
        tabLayout.getTabAt(0)?.setText(resources.getString(R.string.bookmark))
        tabLayout.getTabAt(1)?.setText(resources.getString(R.string.history))

        setSupportActionBar(titleBar)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        titleBar?.setNavigationOnClickListener {
            onBackPressed()
        }
        tvFinish?.setOnClickListener {
            //            var animator = ObjectAnimator.ofFloat(tvFinish, "translationX", -50f)
//            animator?.setDuration(500)
//            animator?.
            tvFinish?.animate()
                    ?.translationX(30f)
                    ?.alpha(0f)
                    ?.setDuration(400)?.withEndAction {
                tvFinish?.visibility = View.GONE
            }
            tvClear?.setText(resources?.getString(R.string.clear))
            tvClear?.setTextColor(Color.BLACK)
            EventBus.getDefault().post(HistoryFinishEvent())


        }
        tvClear?.setOnClickListener {
            if (tvClear?.text?.equals(resources?.getString(R.string.clear))!!) {
                EventBus.getDefault().post(ClearHistoryEvent())
            } else if (tvClear?.text?.equals(resources?.getString(R.string.delete))!!) {
                EventBus.getDefault().post(DeleteHistoryEvent())
            }
        }
    }

    fun setIndicator(tabs: TabLayout, leftDip: Int, rightDip: Int) {
        var tabLayout = tabs.javaClass
        var tabStrip: Field? = null
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip")
        } catch (e: Exception) {
            Log.e(mTAG, "e1:" + e.toString())
        }
        tabStrip?.isAccessible = true
        var llTab: LinearLayout? = null
        try {
            llTab = tabStrip?.get(tabs) as LinearLayout
        } catch (e: Exception) {
            Log.e(mTAG, "e2:" + e.toString())
        }
        var left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip.toFloat(), Resources.getSystem().getDisplayMetrics())
        var right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip.toFloat(), Resources.getSystem().getDisplayMetrics())
        var i = 0
        while (i < llTab?.childCount!!) {
            var child = llTab?.getChildAt(i)
            child?.setPadding(0, 0, 0, 0)
            var params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1F)
            params.leftMargin = left.toInt()
            params.rightMargin = right.toInt()
            child?.layoutParams = params
            child?.invalidate()
            i++
        }
        Log.e(mTAG, "setIndicator")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe
    fun showStatus(event: LongClickEvent) {
        tvClear?.setText(resources?.getString(R.string.delete))
        tvClear?.setTextColor(Color.RED)
        tvFinish?.visibility = View.VISIBLE
        tvFinish?.animate()
                ?.translationX(-30f)
                ?.alpha(1f)
                ?.setDuration(400)?.withEndAction {

        }
    }
}