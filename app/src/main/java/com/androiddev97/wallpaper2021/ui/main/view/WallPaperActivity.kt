package com.androiddev97.wallpaper2021.ui.main.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

import com.androiddev97.wallpaper2021.adapter.ViewPaperWallppAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_wall_paper.*
import kotlinx.android.synthetic.main.custom_viewpaper.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.ui.fragment.CategoryFragment
import com.androiddev97.wallpaper2021.ui.fragment.RandomPictureFragment


class WallPaperActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)
        setSupportActionBar(toolBar)
        val fragmentAdapter = ViewPaperWallppAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#009dff"))
        tabs.setTabTextColors(Color.parseColor("#393838"), Color.parseColor("#009dff"))
        tabs.setupWithViewPager(viewPager)
        setUpNavigationDrawer()

    }


    private fun setUpNavigationDrawer() {
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawer_layout, R.string.nav_open, R.string.nav_close)
        actionBarDrawerToggle!!.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Collection -> {
                loadFragment(CategoryFragment())
            }
            R.id.nav_random -> {
                loadFragment(RandomPictureFragment())
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    fun loadFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.androiddev97.wallpaper2021.R.id.frame, fragment!!)
        transaction.commit()
    }


}