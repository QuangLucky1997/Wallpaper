package com.androiddev97.wallpaper2021.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.androiddev97.wallpaper2021.ui.fragment.CategoryFragment
import com.androiddev97.wallpaper2021.ui.fragment.PopularFragment
import com.androiddev97.wallpaper2021.ui.fragment.RandomPictureFragment

internal class ViewPaperWallppAdapter(var context: Context,
                                      fm: FragmentManager,
                                      var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PopularFragment()
            }
            1 -> {
                CategoryFragment()
            }
            2-> {
                RandomPictureFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
