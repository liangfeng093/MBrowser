package com.liangfeng.mbrowser.view.activity

import android.content.res.Resources
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.view.adapter.BookmarkAdapter
import com.liangfeng.mbrowser.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_history.*
import java.lang.reflect.Field

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
class HistoryActivity : BaseActivity() {
    override fun setPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val mTAG = "HistoryActivity"

    override fun setLayout(): Int {
        return R.layout.activity_history
    }

    override fun initView() {

        var fragments = listOf<Fragment>(HomeFragment(), HomeFragment())
        vpHistory.adapter = BookmarkAdapter(supportFragmentManager, fragments)

        tabLayout.post {
            setIndicator(tabLayout, 35, 35)
        }


        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.setupWithViewPager(vpHistory)


        tabLayout.getTabAt(0)?.setText(resources.getString(R.string.bookmark))
        tabLayout.getTabAt(1)?.setText(resources.getString(R.string.history))

    }

    override fun setListener() {

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
}