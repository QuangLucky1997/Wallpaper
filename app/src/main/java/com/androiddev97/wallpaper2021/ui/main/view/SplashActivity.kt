package com.androiddev97.wallpaper2021.ui.main.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.androiddev97.wallpaper2021.R
import com.androiddev97.wallpaper2021.utils.ConnectionLiveData

class SplashActivity : Activity() {
    private lateinit var connectionLiveData: ConnectionLiveData

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, WallPaperActivity::class.java))
            finish()
        }, 2000)


    }


    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun checkConnectionInternet() {
        connectionLiveData = ConnectionLiveData(application)
//        connectionLiveData.observe(this,), { isConnected ->
//            if (isConnected) {
//                Handler(Looper.getMainLooper()).postDelayed({
//                    startActivity(Intent(this, WallPaperActivity::class.java))
//                    finish()
//                }, 2000)
//            }else
//            {
//
//            }
//        })

    }

}