package com.androiddev97.wallpaper2021.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev97.wallpaper2021.R

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.androiddev97.wallpaper2021.adapter.SlideAdapter
import com.androiddev97.wallpaper2021.data.model.pexel.PhotoList
import kotlinx.android.synthetic.main.activity_showfull.*
import kotlin.math.abs


class ShowFullActivity : AppCompatActivity() {

    private lateinit var url: String
    private lateinit var description: String

    private lateinit var adapter: SlideAdapter
    private var slideHandler = Handler()

    private val photos by lazy { (intent.getSerializableExtra(DATA_IMAGE) as? PhotoList)?.photos ?: emptyList() }

    companion object {
        const val DATA_IMAGE = "picture"
        const val DATA_DES = "des"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_full_activity)
        initData()
       // initListener()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

//    private fun initData() {
//        url = intent.getStringExtra(DATA_IMAGE).toString()
//        description = intent.getStringExtra(DATA_DES).toString()
//        Glide.with(applicationContext).load(url)
//            .override(800, 800).diskCacheStrategy(
//                DiskCacheStrategy.AUTOMATIC
//            ).into(img_Walpaper)
//        if (description != "null") {
//            txt_thumb.text = description
//        } else {
//            txt_thumb.visibility = View.GONE
//        }
//    }


//    private fun initListener() {
//        group_back.setOnClickListener {
//            onBackPressed()
//        }
//        card_set_wallpaper.setOnClickListener {
//            val bitMap: Bitmap = img_Walpaper.drawable.toBitmap()
//            val wallpaperManager: WallpaperManager =
//                WallpaperManager.getInstance(applicationContext)
//            wallpaperManager.setBitmap(bitMap)
//            Toast.makeText(this, "Set Wallpaper Success!!", Toast.LENGTH_SHORT).show()
//        }
//        card_down.setOnClickListener {
//            val intentProcess = Intent(this, ProcessDownloadActivity::class.java)
//            intentProcess.putExtra(PICTURES, url)
//            startActivity(intentProcess)
//        }
//        card_share.setOnClickListener {
//        val bitMap: Bitmap = img_Walpaper.drawable.toBitmap()
//        val intent = Intent(Intent.ACTION_SEND).setType("image/*")
//        val bytes = ByteArrayOutputStream()
//        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path = MediaStore.Images.Media.insertImage(
//            this.contentResolver,
//            bitMap,
//            "WallpaperHD",
//            null
//        )
//        val uri = Uri.parse(path)
//        intent.putExtra(Intent.EXTRA_STREAM, uri)
//        startActivity(intent)
//
//
//        }
//
//    }

    private fun initData()
    {
        adapter = SlideAdapter(this, photos)
        viewPager.adapter = adapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY= 0.8f + r * 0.15f
        }
        viewPager.setPageTransformer(compositePageTransformer)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                slideHandler.removeCallbacks(r)
                slideHandler.postDelayed(r, 3000)

            }
        })
    }

    val r: Runnable = Runnable { viewPager.currentItem = viewPager.currentItem + 1 }



}