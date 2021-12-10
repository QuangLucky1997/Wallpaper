package com.androiddev97.wallpaper2021.data.repository

import com.androiddev97.wallpaper2021.data.api.ApiHelper

class UnplashRepository(private val apiHelper: ApiHelper) {
    suspend fun getPictures(client_id:String, page:Int, per_page:Int)=apiHelper.getDataPictures(client_id,page,per_page)
}