package com.liangfeng.mbrowser.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by mzf on 2017/11/29.
 * Email:liangfeng093@gmail.com
 */
public class BookmarkAdapter : FragmentPagerAdapter {

    constructor(fm: FragmentManager, list: List<Fragment>) : super(fm) {
        this.list = list
    }

    var list: List<Fragment>? = null


    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return list?.get(position)!!
    }

    override fun getCount(): Int {
       return list?.size!!
    }
}