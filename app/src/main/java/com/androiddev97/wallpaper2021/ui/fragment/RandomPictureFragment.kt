package com.androiddev97.wallpaper2021.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.AdapterRandomPictures
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.api.RetrofitBuilder
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.ui.base.ServerViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.view.ShowFullActivity
import com.androiddev97.wallpaper2021.ui.main.viewmodel.ServerViewModel
import com.androiddev97.wallpaper2021.utils.ConnectionLiveData
import com.androiddev97.wallpaper2021.utils.Resources
import com.androiddev97.wallpaper2021.utils.Status
import kotlinx.android.synthetic.main.random_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomPictureFragment : Fragment(), CLickListener {
    private lateinit var randomPicturesViewModel: ServerViewModel
    private lateinit var adapterRandomPictures: AdapterRandomPictures
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
        if (activity != null) {
            setUpObserver()
        }

    }

    private fun setUpObserver() {
        randomPicturesViewModel.getPicturesPexel(1, 80)
            .observe(requireActivity(), { data ->
                getDataRandom(data)
            })
    }

    private fun setUpViewModel() {
        val viewModelWeatherFactory =
            ServerViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        randomPicturesViewModel =
            ViewModelProvider(
                requireActivity(),
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
                            adapterRandomPictures.setDataListImage(listImagePexel)
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
        adapterRandomPictures = AdapterRandomPictures(requireActivity(), this, arrayListOf())
        val layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recycleViewRandom.layoutManager = layoutManager
        recycleViewRandom.setHasFixedSize(true)
        recycleViewRandom.adapter = adapterRandomPictures
    }


    override fun onClick(wallPaper: WallPaper) {
    }

    override fun onClickShowFull(infoImage: InfoImage) {
    }

    override fun onClickRandom(photo: Photo) {
        val intentRandom = Intent(activity, ShowFullActivity::class.java)
        intentRandom.putExtra(ShowFullActivity.DATA_IMAGE, photo.src.portrait)
        startActivity(intentRandom)
    }


}