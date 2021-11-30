package com.androiddev97.wallpaper2021.ui.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.androiddev97.wallpaper2021.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.custom_download.*
import android.util.Log
import com.androiddev97.wallpaper2021.ui.main.view.ProcessDownloadActivity
import com.androiddev97.wallpaper2021.ui.main.view.ProcessDownloadActivity.Companion.PICTURES
import com.androiddev97.wallpaper2021.ui.main.view.SetWallpaperActivity

class BottomSheetDownload(private val url_image: String) : BottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        img_close.setOnClickListener {
            this.dismiss()
        }
        group_set_download.setOnClickListener {
            val intentProcess = Intent(requireActivity(), ProcessDownloadActivity::class.java)
            intentProcess.putExtra(ProcessDownloadActivity.PICTURES, url_image)
            startActivity(intentProcess)
        }
        group_set_home.setOnClickListener {
            val intentProcess = Intent(requireActivity(), SetWallpaperActivity::class.java)
            intentProcess.putExtra(SetWallpaperActivity.DATA_IMAGE, url_image)
            startActivity(intentProcess)
        }
        group_set_lock.setOnClickListener {
            val intentProcess = Intent(requireActivity(), SetWallpaperActivity::class.java)
            intentProcess.putExtra(SetWallpaperActivity.DATA_IMAGE, url_image)
            startActivity(intentProcess)
        }
    }


}