package com.liangfeng.mbrowser.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liangfeng.mbrowser.base.BaseApplication
import com.liangfeng.mbrowser.base.BaseView
import com.liangfeng.mbrowser.event.BackEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by mzf on 2017/11/21.
 * Email:liangfeng093@gmail.com
 */
abstract class BaseFragment : Fragment() {

    var rootView: View? = null
    val TAG = "BaseFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册eventbus
//        EventBus.getDefault().register(activity)

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater?.inflate(setLayout(), container, false)
        Log.e(TAG, "onCreateView")
        initView()
        setListener()
        return rootView
    }

    abstract fun setListener()

    abstract fun initView()

    abstract fun setLayout(): Int

    override fun onStart() {
        super.onStart()

    }

    @Subscribe
    fun base(event: BackEvent) {

    }

    override fun onDestroy() {
        super.onDestroy()
        //注销eventbus
//        EventBus.getDefault().unregister(activity)
    }
}