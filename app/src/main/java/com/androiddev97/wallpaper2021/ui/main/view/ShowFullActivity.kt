package com.androiddev97.wallpaper2021.ui.main.view

import android.app.WallpaperManager
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
import kotlinx.android.synthetic.main.custom_view_imagelist.card_view_setWall
import android.provider.MediaStore
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import java.io.ByteArrayOutputStream


class ShowFullActivity : AppCompatActivity() {

    private lateinit var url : String

    companion object {
        const val DATA_IMAGE = "picture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_view_imagelist)
        initData()
        initListener()
    }

    private fun initData() {
        url = intent.getStringExtra(DATA_IMAGE).toString()
        Glide.with(applicationContext).load(url)
            .override(600, 800).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(img_Walpaper)
    }

    private fun initListener() {
        group_back.setOnClickListener {
            onBackPressed()
        }
        card_view_setWall.setOnClickListener {
            val bitMap: Bitmap = img_Walpaper.drawable.toBitmap()
            val wallpaperManager: WallpaperManager =
                WallpaperManager.getInstance(applicationContext)
            wallpaperManager.setBitmap(bitMap)
            Toast.makeText(this, "Set Wallpaper Success!!", Toast.LENGTH_SHORT).show()
        }
        group_download.setOnClickListener {
            val intentProcess = Intent(this, ProcessDownloadActivity::class.java)
            intentProcess.putExtra(PICTURES, url)
            startActivity(intentProcess)
        }
        group_share.setOnClickListener {
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