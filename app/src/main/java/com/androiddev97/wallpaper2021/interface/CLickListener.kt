package com.androiddev97.wallpaper2021.`interface`

import com.androiddev97.wallpaper2021.data.model.firebase.InfoImage
import com.androiddev97.wallpaper2021.data.model.firebase.WallPaper
import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash

interface CLickListener {
    fun onClick(wallPaper: WallPaper)
    fun onClickShowFull(infoImage: InfoImage)
    fun onClickRandom(photo: Photo)
    fun onClickSearch(reponseUnplash: ReponseUnplash)

}