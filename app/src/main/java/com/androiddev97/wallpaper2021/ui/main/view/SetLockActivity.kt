package com.androiddev97.wallpaper2021.ui.main.view

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import com.androiddev97.wallpaper2021.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_set_lock.*

class SetLockActivity : AppCompatActivity() {
    var dataSetLock: String? = null


    companion object {
        const val DATA_IMAGE_SET_LOCK = "setLock"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_lock)
        dataSetLock = intent.getStringExtra(DATA_IMAGE_SET_LOCK)
        Log.d("MainLock", dataSetLock.toString())
        Glide.with(applicationContext).load(dataSetLock).diskCacheStrategy(
            DiskCacheStrategy.AUTOMATIC
        ).into(img_full_lock)
        initListener()
    }

    private fun initListener() {
        card_view_setLock.setOnClickListener {
            val bitMap: Bitmap = img_full_lock.drawable.toBitmap()
            val wallpaperManager: WallpaperManager =
                WallpaperManager.getInstance(applicationContext)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_NOTIFICATION_POLICY
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    wallpaperManager.setBitmap(bitMap, null, true, WallpaperManager.FLAG_LOCK)
                }

            }
            Toast.makeText(this, "Set Lock Success!!", Toast.LENGTH_SHORT).show()
        }
    }
}