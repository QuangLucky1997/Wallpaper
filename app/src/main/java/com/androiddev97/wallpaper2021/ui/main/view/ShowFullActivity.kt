package com.androiddev97.wallpaper2021.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.ui.fragment.BottomSheetDownload
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.custom_view_imagelist.*


class ShowFullActivity : AppCompatActivity() {
    private var pictureID: InfoImage? = null

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
        pictureID = intent.getSerializableExtra(DATA_IMAGE) as InfoImage
        Glide.with(applicationContext).load(pictureID!!.url)
            .override(600, 800).diskCacheStrategy(
                DiskCacheStrategy.AUTOMATIC
            ).into(image_list_click)
    }

    private fun initListener() {
        img_back.setOnClickListener {
            onBackPressed()
        }
        group_download.setOnClickListener {
            val bottomSheetDownload = BottomSheetDownload(pictureID!!.url)
            bottomSheetDownload.show(
                supportFragmentManager,
                bottomSheetDownload.tag
            )
        }

    }


}