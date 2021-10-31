package com.androiddev97.wallpaper2021.`interface`

import android.view.View
import com.androiddev97.wallpaper2021.data.model.InfoImage
import com.androiddev97.wallpaper2021.data.model.WallPaper

interface CLickListener {
    fun onClick(wallPaper: WallPaper)
    fun onClickShowFull(infoImage: InfoImage)

}