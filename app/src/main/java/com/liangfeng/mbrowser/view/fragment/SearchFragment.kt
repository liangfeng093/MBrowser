package com.liangfeng.mbrowser.view.fragment

import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.BackEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.event.WebEvent
import com.liangfeng.mbrowser.network.Url
import com.vondear.rxtools.RxKeyboardTool
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/23.
 * Email:liangfeng093@gmail.com
 */
class SearchFragment : BaseFragment(), TextWatcher {


    /************** view *****************/
    var etSearchBar: EditText? = null
    var contains: RelativeLayout? = null
    var devidLine: LinearLayout? = null
    var search_container: LinearLayout? = null
    var tvSearchCancel: TextView? = null

    /************** Event *****************/
    var eventBack: BackEvent? = BackEvent()
    var webEvent: WebEvent? = WebEvent()
    var replaceFragment: ReplaceFragmentEvent? = ReplaceFragmentEvent()

    /************** flag *****************/
    var isCancel: Boolean = false


    var keyWords: String = ""

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
        tvSearchCancel = rootView?.findViewById<TextView>(R.id.tvSearchCancel)


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        contains?.setOnClickListener {
            jumpTo(ReplaceFragmentEvent.HOME_FRAGMENT)
        }
        tvSearchCancel?.setOnClickListener {
            Log.e(TAG, "666")
            if (isCancel) {
                jumpTo(ReplaceFragmentEvent.HOME_FRAGMENT)
            } else {
                replaceFragment?.keyWords = etSearchBar?.text.toString()
                jumpTo(ReplaceFragmentEvent.WEB_FRAGMENT)
            }
        }

        etSearchBar?.addTextChangedListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun jumpTo(tag: Int) {
        RxKeyboardTool.hideSoftInput(activity, etSearchBar)
        replaceFragment?.type = tag
        search_container?.animate()
                ?.translationY(50f)
                ?.alpha(0f)
                ?.setDuration(400)?.withEndAction {
            EventBus.getDefault().post(replaceFragment)
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        Log.e(TAG, "afterTextChanged-p0:" + p0)

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if (p0?.isEmpty()!!) {
            Log.e(TAG, "取消")
            isCancel = true
            tvSearchCancel?.text = resources.getString(R.string.search_cancel)
        } else {
            Log.e(TAG, "搜索")
            isCancel = false
            tvSearchCancel?.text = resources.getString(R.string.search_)
        }
    }
}