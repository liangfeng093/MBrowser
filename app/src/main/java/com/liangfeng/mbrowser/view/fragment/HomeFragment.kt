package com.liangfeng.mbrowser.view.fragment

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.ImageView
import com.liangfeng.mbrowser.R
import com.liangfeng.mbrowser.event.BackEvent
import com.liangfeng.mbrowser.event.ReplaceFragmentEvent
import com.liangfeng.mbrowser.widget.HomeView
import com.liangfeng.mbrowser.widget.HomeView.HomeViewClick
import org.greenrobot.eventbus.EventBus

/**
 * Created by mzf on 2017/11/22.
 * Email:liangfeng093@gmail.com
 */
class HomeFragment : BaseFragment(), HomeViewClick {



    var homeView: HomeView? = null
    /*var etSearchBar: EditText? = null
    var contains: RelativeLayout? = null
    var devidLine: LinearLayout? = null*/
    var ivSearchBar: ImageView? = null

    var eventBack: BackEvent? = BackEvent()
    var replaceFragment: ReplaceFragmentEvent? = ReplaceFragmentEvent()



    override fun onViewCilik() {
        Log.e(MYTAG, "666")
        homeView?.animate()
                ?.translationY(50f)
                ?.setDuration(500)
        Thread.sleep(500)
        EventBus.getDefault().post(replaceFragment)

    }


    override fun initView() {
        ivSearchBar = rootView?.findViewById<ImageView>(R.id.ivSearchBar)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setListener() {
        ivSearchBar?.setOnClickListener {

            ivSearchBar?.animate()
                    ?.translationY(50f)
                    ?.alpha(0f)
                    ?.setDuration(300)?.withEndAction {
                replaceFragment?.type = ReplaceFragmentEvent.SEARCH_FRAGMENT
                EventBus.getDefault().post(replaceFragment)
            }
        }
    }

    val MYTAG = "HomeFragment"


    override fun setPresenter() {

    }

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

}