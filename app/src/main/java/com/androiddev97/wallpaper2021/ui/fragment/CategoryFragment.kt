package com.androiddev97.wallpaper2021.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.WallpaperAdapter
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.androiddev97.wallpaper2021.ui.base.WallPaperViewModelFactory
import com.androiddev97.wallpaper2021.ui.main.view.DetailsListPicturesActivity
//import com.androiddev97.wallpaper2021.ui.main.view.DetailsListPicturesActivity
import com.androiddev97.wallpaper2021.ui.main.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.category_fragment.*


class CategoryFragment : Fragment(), CLickListener {
    private lateinit var adapterWallPaper: WallpaperAdapter
    private var categoryViewModel: CategoryViewModel? = null
    private val viewModelWallPaper by lazy {
        ViewModelProviders.of(requireActivity()).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
        setUpViewModel()
        if (activity != null) {
            observeData()
        }


    }

    private fun setUpViewModel() {
        val firebaseViewModelFactory = WallPaperViewModelFactory()
        categoryViewModel = ViewModelProvider(
            requireActivity(),
            firebaseViewModelFactory
        )[CategoryViewModel::class.java]
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observeData() {
        viewModelWallPaper.fetchDataFromFireBase().observe(requireActivity(), {
            ProgressBarShow.visibility = View.GONE
            adapterWallPaper.setData(it)
            adapterWallPaper.notifyDataSetChanged()
        })
    }

    private fun setUpRecycleView() {
        adapterWallPaper = WallpaperAdapter(this, requireActivity())
        recycleViewWallpaper.setHasFixedSize(true)
        recycleViewWallpaper.layoutManager =
            GridLayoutManager(activity, 2)
        recycleViewWallpaper.adapter = adapterWallPaper
    }

    private fun showText() {
        lottiesShow.visibility = View.VISIBLE
    }


    override fun onClick(wallPaper: WallPaper) {
        val intentData = Intent(activity, DetailsListPicturesActivity::class.java)
        intentData.putExtra(DetailsListPicturesActivity.ID_DATA, wallPaper)
        startActivity(intentData)
    }

    override fun onClickShowFull(infoImage: InfoImage) {
    }

    override fun onClickRandom(photo: Photo) {
    }

    override fun onClickPopular(popular: Popular) {
        TODO("Not yet implemented")
    }


}