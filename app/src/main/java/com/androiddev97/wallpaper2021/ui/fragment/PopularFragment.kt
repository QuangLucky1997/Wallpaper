package com.androiddev97.wallpaper2021.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.`interface`.CLickListener
import com.androiddev97.wallpaper2021.adapter.AdapterPopular
import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.popular.Popular
import com.androiddev97.wallpaper2021.ui.main.view.PopularListActivity
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(), CLickListener {
    private var listPopular: ArrayList<Popular>? = null
    private lateinit var adapterPopular: AdapterPopular
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_popular, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fakeData()
        adapterPopular = AdapterPopular(requireActivity(), this, listPopular!!)
        recycle_view_popular.setHasFixedSize(true)
        recycle_view_popular.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recycle_view_popular.adapter = adapterPopular

    }

    private fun fakeData() {
        listPopular = ArrayList()
        listPopular!!.add(Popular(R.drawable.river, "River"))
        listPopular!!.add(Popular(R.drawable.animal, "Animals"))
        listPopular!!.add(Popular(R.drawable.neon, "Neon"))
        listPopular!!.add(Popular(R.drawable.winter, "Winter"))
        listPopular!!.add(Popular(R.drawable.chamngon, "Quotes"))
        listPopular!!.add(Popular(R.drawable.sports, "Sports"))
        listPopular!!.add(Popular(R.drawable.plane, "Plane"))
        listPopular!!.add(Popular(R.drawable.city, "City"))
        listPopular!!.add(Popular(R.drawable.game, "Game"))
        listPopular!!.add(Popular(R.drawable.kt, "Architecture"))
    }

    override fun onClick(wallPaper: WallPaper) {

    }

    override fun onClickShowFull(infoImage: InfoImage) {

    }

    override fun onClickRandom(photo: Photo) {

    }

    override fun onClickPopular(popular: Popular) {
        val intentPopular = Intent(requireActivity(), PopularListActivity::class.java)
        intentPopular.putExtra(PopularListActivity.DATA_POPULAR, popular.namePopular)
        startActivity(intentPopular)
    }

    override fun sendList(respone: PexelReponse) {

    }


}