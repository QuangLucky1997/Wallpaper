package com.androiddev97.wallpaper2021.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.adapter.ListPhotosAdapter
import com.androiddev97.wallpaper2021.data.model.InfoImage
//import com.androiddev97.wallpaper2021.adapter.DetailImageAdapter
import com.androiddev97.wallpaper2021.ui.main.viewmodel.CategoryViewModel
//import com.androiddev97.wallpaper2021.ui.main.view.DetailsListPicturesActivity

class ShowFullActivity : AppCompatActivity() {
    private var pictureID:InfoImage? = null
    private lateinit var listPhotosAdapter: ListPhotosAdapter


    private val viewModelWallPaper by lazy {
        ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }

    companion object {
        const val DATA_IMAGE = "picture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_photos_activity)
        pictureID = intent.getSerializableExtra(DATA_IMAGE) as InfoImage
        Log.d("abc89012", pictureID!!.id)
//        recycle_view_list_photos.setHasFixedSize(true)
//        recycle_view_list_photos.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        listPhotosAdapter = ListPhotosAdapter(this)
//        recycle_view_list_photos.adapter = listPhotosAdapter
        //observeDataListPhotos()
    }



}