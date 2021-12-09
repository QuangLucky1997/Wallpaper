package com.androiddev97.wallpaper2021.ui.main.view

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.androiddev97.wallpaper2021.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_set_lock.*
import kotlinx.android.synthetic.main.activity_set_wallpaper.*

class SetWallpaperActivity : AppCompatActivity() {
    var dataSet: String? = null

    companion object {
        const val DATA_IMAGE = "setWall"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_wallpaper)
        dataSet = intent.getStringExtra(DATA_IMAGE)
        Glide.with(applicationContext).load(dataSet).override(720,1280).diskCacheStrategy(
            DiskCacheStrategy.AUTOMATIC
        ).into(img_full)
        initListener()
    }

    private fun initListener() {
        card_view_setWall.setOnClickListener {
            val bitMap: Bitmap = img_full.drawable.toBitmap()
            val wallpaperManager: WallpaperManager =
                WallpaperManager.getInstance(applicationContext)
            wallpaperManager.setBitmap(bitMap)
            Toast.makeText(this, "Set Wallpaper Success!!", Toast.LENGTH_SHORT).show()
        }
    }


}