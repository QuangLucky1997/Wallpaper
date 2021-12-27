package com.androiddev97.wallpaper2021.ui.main.view

import SearchAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.api.RetrofitBuilder
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.androiddev97.wallpaper2021.ui.base.ServerViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.viewmodel.ServerViewModel
import com.androiddev97.wallpaper2021.utils.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity(), CLickListener {

    private lateinit var searchPicturesViewModel: ServerViewModel
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initListener()
        setUpRecyclerView()
        setUpViewModel()
        searchData.setOnClickListener {
            setUpObserver()
            EditSearch.hideKeyboard()
        }
    }
    private fun setUpObserver() {
        searchPicturesViewModel.searchPictures(EditSearch.text.trim().toString(), 80)
            .observe(this, { data ->
                getDataRandom(data)
            })
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
                            val listImagePexel = data!!.photos
                            searchAdapter.setDataListImage(listImagePexel)
                            text.visibility = View.GONE
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
        searchAdapter = SearchAdapter(this, this, arrayListOf())
        val layoutManager = GridLayoutManager(
            applicationContext,
            3
        )
        recycleViewSearch.layoutManager = layoutManager
        recycleViewSearch.setHasFixedSize(true)
        recycleViewSearch.adapter = searchAdapter
    }

    private fun initListener() {
        EditSearch.showKeyboard()
    }

    override fun onClick(wallPaper: WallPaper) {
    }

    override fun onClickShowFull(infoImage: InfoImage) {
    }

    override fun onClickRandom(photo: Photo) {
        val intentRandom = Intent(this, ShowFullActivity::class.java)
        intentRandom.putExtra(ShowFullActivity.DATA_IMAGE, photo.src.portrait)
        startActivity(intentRandom)
    }

    override fun onClickPopular(popular: Popular) {

    }


}