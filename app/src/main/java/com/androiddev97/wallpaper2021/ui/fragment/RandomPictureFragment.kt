package com.androiddev97.wallpaper2021.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androiddev97.wallpaper2021.data.api.ApiHelper
import com.androiddev97.wallpaper2021.data.api.RetrofitBuilder
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import com.androiddev97.wallpaper2021.data.repository.UnplashRepository
import com.androiddev97.wallpaper2021.ui.base.UnplashViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.viewmodel.UnplashViewModel
import com.androiddev97.wallpaper2021.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomPictureFragment : Fragment() {
    private lateinit var randomPicturesViewModel: UnplashViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        randomPicturesViewModel.getPictures("U7EEpMH-94ZXAG1GHvr83krUhZm2Ljgeprm3tTM-jA0", 1, 200)
            .observe(requireActivity(), { data ->
                getDataRandom(data)
            })
    }
    private fun setUpViewModel()
    {
        val viewModelWeatherFactory =
            UnplashViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        randomPicturesViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelWeatherFactory
            ).get(UnplashViewModel::class.java)
    }

    private fun getDataRandom(it: com.androiddev97.wallpaper2021.utils.Resources<ReponseUnplash>) {
        it.let {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.Main)
                        {

                        }
                    }
                }
                Status.LOADING -> {
                   // progressBar.visibility = View.GONE
                    Log.e("Loading", "${it.message}")
                }
                Status.ERROR -> {
                    Log.e("error", "${it.message}")
                }
            }
        }
    }
    private fun setDataAdapter(reponseUnplash: ReponseUnplash)
    {

    }
}