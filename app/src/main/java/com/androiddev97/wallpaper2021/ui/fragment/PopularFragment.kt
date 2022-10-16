package com.androiddev97.wallpaper2021.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
    private var listPopularHeader: ArrayList<Popular>? = null
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
        setHeaderRv()
        eventTouchBottom()

    }

    private fun eventTouchBottom() {
        view_item_category_river.setOnClickListener {
            sendDataBottom("River")
        }
        view_item_category_animals.setOnClickListener {
            sendDataBottom("Animals")
        }
        view_item_sports.setOnClickListener {
            sendDataBottom("Sports")
        }
        view_item_category_plane.setOnClickListener {
            sendDataBottom("Plan")
        }
        view_item_neon.setOnClickListener {
            sendDataBottom("Neon")
        }
        view_item_category_city.setOnClickListener {
            sendDataBottom("City")
        }
        view_item_category_winter.setOnClickListener {
            sendDataBottom("Winter")
        }
        view_item_category_quotes.setOnClickListener {
            sendDataBottom("Quotes")
        }
    }

    private fun setHeaderRv() {
        adapterPopular = AdapterPopular(requireActivity(), this, listPopularHeader!!)
        rv_header.adapter = adapterPopular
        rv_header.set3DItem(true)
        rv_header.setAlpha(true)
        rv_header.setInfinite(true)
        rv_header.getCarouselLayoutManager()
        rv_header.getSelectedPosition()

    }


    private fun fakeData() {
        listPopularHeader = ArrayList()
        listPopularHeader!!.add(Popular(R.drawable.bird, "Birds", 80))
        listPopularHeader!!.add(Popular(R.drawable.boy, "Boys", 90))
        listPopularHeader!!.add(Popular(R.drawable.girls, "Girls", 67))
        listPopularHeader!!.add(Popular(R.drawable.moutain, "Mountain", 100))
        listPopularHeader!!.add(Popular(R.drawable.coin, "Digital currency", 60))
        listPopularHeader!!.add(Popular(R.drawable.rain, "Rain", 300))
        listPopularHeader!!.add(Popular(R.drawable.arts, "Arts", 250))
        listPopularHeader!!.add(Popular(R.drawable.space, "Space", 120))
        listPopularHeader!!.add(Popular(R.drawable.rose, "Flowers", 567))
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

    private fun sendDataBottom(title: String) {
        val intentPopularBottom = Intent(requireActivity(), PopularListActivity::class.java)
        intentPopularBottom.putExtra(PopularListActivity.DATA_POPULAR, title)
        startActivity(intentPopularBottom)
    }


}