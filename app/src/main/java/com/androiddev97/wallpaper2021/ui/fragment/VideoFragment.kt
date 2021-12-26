package com.androiddev97.wallpaper2021.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.net.Uri

import android.widget.VideoView
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.androiddev97.wallpaper2021.R.layout.fragment_video,container,false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val uri = "https://www.pexels.com/video/time-lapse-video-sunset-856973/"
        if (video_load_view != null) {
            video_load_view.setVideoURI(Uri.parse(uri))
            video_load_view.requestFocus()
            video_load_view.start()
        } else { //toast or print "mVideoView is null"
        }
    }


}