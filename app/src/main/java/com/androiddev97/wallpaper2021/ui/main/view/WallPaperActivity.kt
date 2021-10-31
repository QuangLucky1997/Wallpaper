package com.androiddev97.wallpaper2021.ui.main.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.adapter.ViewPaperWallppAdapter
import kotlinx.android.synthetic.main.activity_wall_paper.*

class WallPaperActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)
        setSupportActionBar(toolBar)
        val fragmentAdapter = ViewPaperWallppAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#009dff"))
        tabs.setTabTextColors(Color.parseColor("#FFFFFFFF"), Color.parseColor("#009dff"))
        tabs.setupWithViewPager(viewPager)

    }

}