package com.androiddev97.wallpaper2021.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.androiddev97.wallpaper2021.ui.fragment.CategoryFragment
import com.androiddev97.wallpaper2021.ui.fragment.RandomPictureFragment

class ViewPaperWallppAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CategoryFragment()
            else -> {
                return RandomPictureFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Collection"
            else -> {
                return "Random WallPaper"
            }
        }


    }
}