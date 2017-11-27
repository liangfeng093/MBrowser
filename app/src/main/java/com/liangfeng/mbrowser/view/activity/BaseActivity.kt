package com.liangfeng.mbrowser.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.base.BaseApplication
import com.liangfeng.mbrowser.base.BaseView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by mzf on 2017/11/21.
 * Email:liangfeng093@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        initView()
        //注册eventbus
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
