package com.androiddev97.wallpaper2021.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.AdapterPopular
import com.androiddev97.wallpaper2021.adapter.AdapterPopularList
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.api.RetrofitBuilder
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.androiddev97.wallpaper2021.ui.base.ServerViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.viewmodel.ServerViewModel
import com.androiddev97.wallpaper2021.utils.Resources
import com.androiddev97.wallpaper2021.utils.Status
import com.androiddev97.wallpaper2021.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_popular_list.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularListActivity : AppCompatActivity(), CLickListener {
    private lateinit var searchPicturesViewModel: ServerViewModel
    private lateinit var adapterPopular: AdapterPopularList
    private var dataPopular: String? = null

    companion object {
        const val DATA_POPULAR = "popular"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_list)
        if (isNetworkAvailable()) {
            setUpRecyclerView()
            setUpViewModel()
            setUpObserver()
        } else {
            Toast.makeText(this, "Please check your internet!!", Toast.LENGTH_LONG).show()
        }

        initListener()
    }

    private fun initListener() {
        imgBackPopular.setOnClickListener {
            onBackPressed()
        }
    }


    private fun setUpObserver() {
        dataPopular = intent.getSerializableExtra(DATA_POPULAR).toString()
        textViewTitlePopular.text = dataPopular!!
        searchPicturesViewModel.searchPictures(dataPopular!!, 80)
            .observe(this) { data ->
                getDataRandom(data)
            }
    }


    private fun setUpViewModel() {
        val viewModelWeatherFactory =
            ServerViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        searchPicturesViewModel =
            ViewModelProvider(
                this,
                viewModelWeatherFactory
            ).get(ServerViewModel::class.java)
    }


    private fun getDataRandom(it: Resources<PexelReponse>) {
        it.let {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.Main)
                        {
                            val listImagePopular = data!!.photos
                            adapterPopular.setDataListImage(listImagePopular)
                        }
                    }
                }
                Status.LOADING -> {
                    Log.e("Loading", "${it.message}")
                }
                Status.ERROR -> {
                    Log.e("error", "${it.message}")
                }
            }
        }
    }


    private fun setUpRecyclerView() {
        adapterPopular = AdapterPopularList(this, this, arrayListOf())
        val layoutManager = GridLayoutManager(
            applicationContext,
            3
        )
        recycle_view_list_popular.layoutManager = layoutManager
        recycle_view_list_popular.setHasFixedSize(true)
        recycle_view_list_popular.adapter = adapterPopular
    }

    override fun onClick(wallPaper: WallPaper) {
    }

    override fun onClickShowFull(infoImage: InfoImage) {

    }

    override fun onClickRandom(photo: Photo) {
        val intentPopular = Intent(this, ShowFullActivity::class.java)
        intentPopular.putExtra(ShowFullActivity.DATA_DES, photo.alt)
        intentPopular.putExtra(ShowFullActivity.DATA_IMAGE, photo.src.large2x)
        startActivity(intentPopular)
    }

    override fun onClickPopular(popular: Popular) {

    }

    override fun sendList(respone: PexelReponse) {

    }
}