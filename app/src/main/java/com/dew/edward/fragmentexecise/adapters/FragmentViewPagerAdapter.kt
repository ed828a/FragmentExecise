package com.dew.edward.fragmentexecise.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dew.edward.fragmentexecise.fragments.FirstFragment
import com.dew.edward.fragmentexecise.fragments.SecondFragment

/*
 * Created by Edward on 6/8/2018.
 */

class FragmentViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> FirstFragment.newInstance()
            1 -> SecondFragment.newInstance()
            else -> FirstFragment.newInstance()
        }
    }

    override fun getCount(): Int = 2
}