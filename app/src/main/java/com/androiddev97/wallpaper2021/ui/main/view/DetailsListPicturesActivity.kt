package com.androiddev97.wallpaper2021.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.DetailImageAdapter
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.androiddev97.wallpaper2021.ui.base.WallPaperViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.detail_activity.*

class DetailsListPicturesActivity : AppCompatActivity(), CLickListener {
    private lateinit var adapterDetailWallPaper: DetailImageAdapter
    private var dataWallPaper: WallPaper? = null

    private var categoryViewModel: CategoryViewModel? = null

    private val viewModelWallPaper by lazy {
        ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }


    companion object {
        const val ID_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        dataWallPaper = intent.getSerializableExtra(ID_DATA) as WallPaper
        setTitleCollection()
        imgBack.setOnClickListener {
            onBackPressed()
        }
        setUpRecycleViewListCategory()
        setUpViewModel()
        observeDataListPhotos()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeDataListPhotos() {
        viewModelWallPaper.fetchInfoImage(dataWallPaper!!.title).observe(this, {
            adapterDetailWallPaper.setDataListImage(it)
        })
    }


    private fun setTitleCollection() {
        textViewCategory.text = dataWallPaper!!.title
    }

    private fun setUpRecycleViewListCategory() {
        RecycleViewImage.setHasFixedSize(true)
        RecycleViewImage.layoutManager =
            GridLayoutManager(this, 2)
        adapterDetailWallPaper = DetailImageAdapter(applicationContext, this)
        RecycleViewImage.adapter = adapterDetailWallPaper
    }

    private fun setUpViewModel() {
        val firebaseViewModelFactory = WallPaperViewModelFactory()
        categoryViewModel = ViewModelProvider(
            this,
            firebaseViewModelFactory
        )[CategoryViewModel::class.java]
    }

    override fun onClick(wallPaper: WallPaper) {
    }


    override fun onClickShowFull(infoImage: InfoImage) {
        val intentPhotos = Intent(this, ShowFullActivity::class.java)
        intentPhotos.putExtra(ShowFullActivity.DATA_IMAGE, infoImage.url)
        startActivity(intentPhotos)
    }

    override fun onClickRandom(photo: Photo) {
    }

    override fun onClickPopular(popular: Popular) {
    }

    override fun sendList(respone: PexelReponse) {

    }


}