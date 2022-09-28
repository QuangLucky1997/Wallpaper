package com.androiddev97.wallpaper2021.ui.main.view

import android.app.WallpaperManager
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.ui.main.view.ProcessDownloadActivity.Companion.PICTURES
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_view_imagelist.*

import android.provider.MediaStore
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import java.io.ByteArrayOutputStream
import android.view.WindowManager

import android.os.Build
import android.view.View
import android.view.Window


class ShowFullActivity : AppCompatActivity() {

    private lateinit var url: String
    private lateinit var description: String

    companion object {
        const val DATA_IMAGE = "picture"
        const val DATA_DES = "des"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_view_imagelist)
        initData()
        initListener()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    private fun initData() {
        url = intent.getStringExtra(DATA_IMAGE).toString()
        description = intent.getStringExtra(DATA_DES).toString()
        Glide.with(applicationContext).load(url).centerInside().diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(img_Walpaper)
        if (description != "null") {
            txt_thumb.text = description
        } else {
            txt_thumb.visibility = View.GONE
        }
    }


private fun initListener() {
    group_back.setOnClickListener {
        onBackPressed()
    }
    card_set_wallpaper.setOnClickListener {
        val bitMap: Bitmap = img_Walpaper.drawable.toBitmap()
        val wallpaperManager: WallpaperManager =
            WallpaperManager.getInstance(applicationContext)
        wallpaperManager.setBitmap(bitMap)
        Toast.makeText(this, "Set Wallpaper Success!!", Toast.LENGTH_SHORT).show()
    }
    card_down.setOnClickListener {
        val intentProcess = Intent(this, ProcessDownloadActivity::class.java)
        intentProcess.putExtra(PICTURES, url)
        startActivity(intentProcess)
    }
    card_share.setOnClickListener {
        val bitMap: Bitmap = img_Walpaper.drawable.toBitmap()
        val intent = Intent(Intent.ACTION_SEND).setType("image/*")
        val bytes = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            this.contentResolver,
            bitMap,
            "WallpaperHD",
            null
        )
        val uri = Uri.parse(path)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(intent)
    }

}


}