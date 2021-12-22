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
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.androiddev97.wallpaper2021.ui.base.UnplashViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.view.ShowFullActivity
import com.androiddev97.wallpaper2021.ui.main.viewmodel.UnplashViewModel
import com.androiddev97.wallpaper2021.utils.ConnectivityLiveData
import com.androiddev97.wallpaper2021.utils.Resources
import com.androiddev97.wallpaper2021.utils.Status
import kotlinx.android.synthetic.main.random_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomPictureFragment : Fragment(), CLickListener {
    private lateinit var randomPicturesViewModel: UnplashViewModel
    private lateinit var adapterRandomPictures: AdapterRandomPictures
    private lateinit var connectivityLiveData: ConnectivityLiveData
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        connectivityLiveData.observe(requireActivity(), { isAvailable ->
            when (isAvailable) {
                true -> {
                    setUpRecyclerView()
                    setUpViewModel()
                    setUpObserver()
                }
            }
        })

    }

//    private fun showText() {
//        lottiesShow_2.visibility = View.VISIBLE
//    }

    private fun setUpObserver() {
        randomPicturesViewModel.getPictures("U7EEpMH-94ZXAG1GHvr83krUhZm2Ljgeprm3tTM-jA0", 1, 80)
            .observe(requireActivity(), { data ->
                getDataRandom(data)
            })
    }

    private fun setUpViewModel() {
        val viewModelWeatherFactory =
            UnplashViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        randomPicturesViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelWeatherFactory
            ).get(UnplashViewModel::class.java)
    }

    private fun getDataRandom(it: Resources<List<ReponseUnplash>>) {
        it.let {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.Main)
                        {
                            adapterRandomPictures.setDataListImage(data as MutableList<ReponseUnplash>)
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
        val layoutManager =  StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        layoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recycleViewRandom.layoutManager = layoutManager

        recycleViewRandom.setHasFixedSize(true)
        recycleViewRandom.adapter = adapterRandomPictures
    }


    override fun onClick(wallPaper: WallPaper) {
    }

    override fun onClickShowFull(infoImage: InfoImage) {
    }

    override fun onClickRandom(reponseUnplash: ReponseUnplash) {
            val intentRandom= Intent(activity, ShowFullActivity::class.java)
            intentRandom.putExtra(ShowFullActivity.DATA_IMAGE,reponseUnplash.urls.regular)
            startActivity(intentRandom)
    }
}