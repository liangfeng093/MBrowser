package com.liangfeng.mbrowser.view.fragment

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.gson.Gson
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.BackEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.event.WebEvent
import com.liangfeng.mbrowser.module.PromptKeywordBean
import com.liangfeng.mbrowser.network.RetrofitManager
import com.liangfeng.mbrowser.network.RetrofitManager.Companion.promptKeyWords
import com.liangfeng.mbrowser.network.observer.NetworkObserver
import com.liangfeng.mbrowser.view.adapter.SearchAdapter
import com.vondear.rxtools.RxKeyboardTool
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_search.*
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/23.
 * Email:liangfeng093@gmail.com
 */
class SearchFragment : BaseFragment(), TextWatcher {


    companion object {
//        var fragment: SearchFragment? = null

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }

    }


    /************** view *****************/
    var etSearchBar: EditText? = null
    var contains: RelativeLayout? = null
    var devidLine: LinearLayout? = null
    var search_container: LinearLayout? = null
    var tvSearchCancel: TextView? = null
    var rvSearch: RecyclerView? = null

    /************** Event *****************/
    var eventBack: BackEvent? = BackEvent()
    var webEvent: WebEvent? = WebEvent()
    var replaceFragment: ReplaceFragmentEvent? = ReplaceFragmentEvent()

    /************** flag *****************/
    var isCancel: Boolean = false

    var mAdapter: SearchAdapter? = null
    var keyWords: MutableList<String>? = null


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
        rvSearch = rootView?.findViewById<RecyclerView>(R.id.rvSearch)

        var layoutManager = LinearLayoutManager(activity)
        rvSearch?.layoutManager = layoutManager
        keyWords = mutableListOf<String>()


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
    fun jumpTo(tag: Int) {
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
            rvSearch?.visibility = View.GONE
            isCancel = true
            tvSearchCancel?.text = resources.getString(R.string.search_cancel)
            keyWords?.clear()
            mAdapter?.notifyDataSetChanged()
        } else {
            Log.e(TAG, "搜索")
            rvSearch?.visibility = View.VISIBLE
            isCancel = false
            tvSearchCancel?.text = resources.getString(R.string.search_)
            promptKeyWords(p0.toString(), object : NetworkObserver() {
                override fun onNext(t: ResponseBody) {
                    var string = t.string()
                    string = string.subSequence(17, string.length - 2) as String?
                    val gosn = Gson()
                    var bean = gosn.fromJson(string, PromptKeywordBean::class.java)
                    keyWords = bean.s?.toMutableList()
                    Log.e(TAG, "keyWords:" + keyWords)
                    Log.e(TAG, "size:" + keyWords?.size)
                    mAdapter = SearchAdapter(this@SearchFragment, R.layout.item_keyword, keyWords)
                    rvSearch?.adapter = mAdapter
                    keyWords?.size?.minus(1)?.let { rvSearch?.scrollToPosition(it) }
//                    mAdapter?.setOnItemClickListener()
                }
            })
        }
    }
}