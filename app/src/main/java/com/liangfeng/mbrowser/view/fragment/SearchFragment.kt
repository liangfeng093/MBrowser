package com.liangfeng.mbrowser.view.fragment

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.EventBack
import com.liangfeng.mbrowser.event.EventReplaceFragment
import com.vondear.rxtools.RxKeyboardTool
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/23.
 * Email:liangfeng093@gmail.com
 */
class SearchFragment : BaseFragment() {

    var etSearchBar: EditText? = null
    var contains: RelativeLayout? = null
    var devidLine: LinearLayout? = null
    var search_container: LinearLayout? = null

    var replaceFragment: EventReplaceFragment? = EventReplaceFragment()
    var eventBack: EventBack? = EventBack()
    override fun setPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLayout(): Int {
        return R.layout.fragment_search
    }


    override fun onResume() {
        super.onResume()
        RxKeyboardTool.showSoftInput(activity, etSearchBar)//确保view绘制完毕

    }

    override fun onPause() {
        super.onPause()
        RxKeyboardTool.hideSoftInput(activity, etSearchBar)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        etSearchBar = rootView?.findViewById<EditText>(R.id.etSearchBar)
        contains = rootView?.findViewById<RelativeLayout>(R.id.contains)
        devidLine = rootView?.findViewById<LinearLayout>(R.id.devidLine)
        search_container = rootView?.findViewById<LinearLayout>(R.id.search_container)


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        contains?.setOnClickListener {

            RxKeyboardTool.hideSoftInput(activity, etSearchBar)
            search_container?.animate()
                    ?.translationY(50f)
                    ?.alpha(0f)
                    ?.setDuration(400)?.withEndAction {
                replaceFragment?.type = EventReplaceFragment.HOME_FRAGMENT
                EventBus.getDefault().post(replaceFragment)
            }
        }
    }

}