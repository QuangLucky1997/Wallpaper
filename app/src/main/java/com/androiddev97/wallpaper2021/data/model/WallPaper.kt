package com.androiddev97.wallpaper2021.data.model

import java.io.Serializable

data class WallPaper(
    val title: String = "",
    val photos: List<InfoImage>,
    val id:String="",
    val imgCategory:String=""

) :
    Serializable
