package com.androiddev97.wallpaper2021.ui.main.view


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat

import com.androiddev97.wallpaper2021.adapter.ViewPaperWallpaperAdapter
import kotlinx.android.synthetic.main.activity_wall_paper.*
import kotlinx.android.synthetic.main.custom_viewpaper.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.androiddev97.wallpaper2021.R
import java.util.*
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import com.androiddev97.wallpaper2021.BuildConfig
import java.lang.Exception
import androidx.annotation.StyleRes


class WallPaperActivity : AppCompatActivity() {


    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)

        setSupportActionBar(toolBar)
        val fragmentAdapter = ViewPaperWallpaperAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#009dff"))
        tabs.setTabTextColors(Color.parseColor("#393838"), Color.parseColor("#009dff"))
        tabs.setupWithViewPager(viewPager)
        setUpNavigationDrawer()

        val drawerSwitch = nav_view.menu.findItem(R.id.nav_dark_mode).actionView as SwitchCompat
        drawerSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        iconSearch.setOnClickListener {
            val intentSearch = Intent(this, SearchActivity::class.java)
            startActivity(intentSearch)
        }

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_Collection -> {
                    Toast.makeText(this, "Have a nice day for you", Toast.LENGTH_SHORT).show()

                }
                R.id.nav_share -> {
                    shareApp()
                }
                R.id.nav_dark_mode -> {

                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "WallpaperHD")
            var shareMessage = "\nShare App your friends\n\n"
            shareMessage =
                """
              ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
              """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
        }
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


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun loadFragment(fragment: Fragment?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment!!)
        transaction.commit()
    }


    private fun setAppTheme(@StyleRes style: Int) {
        setTheme(style)
    }


}