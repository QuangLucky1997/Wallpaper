package com.androiddev97.wallpaper2021.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.adapter.ViewPaper2ListImageAdapter
import com.androiddev97.wallpaper2021.data.model.InfoImage
import kotlinx.android.synthetic.main.list_photos_activity.*


class ShowFullActivity : AppCompatActivity() {
    private var pictureID: InfoImage? = null

    private lateinit var viewPaper2ListImageAdapter: ViewPaper2ListImageAdapter


    companion object {
        const val DATA_IMAGE = "picture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_photos_activity)
        pictureID = intent.getSerializableExtra(DATA_IMAGE) as InfoImage
        ViewPaperListImage.adapter=viewPaper2ListImageAdapter
    }


}