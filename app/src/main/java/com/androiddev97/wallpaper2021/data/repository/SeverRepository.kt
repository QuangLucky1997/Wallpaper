package com.androiddev97.wallpaper2021.data.repository

import com.androiddev97.wallpaper2021.data.api.ApiHelper

class SeverRepository(private val apiHelper: ApiHelper) {
    suspend fun getPictures(client_id: String, page: Int, per_page: Int) =
        apiHelper.getDataPictures(client_id, page, per_page)

    suspend fun getPicturesPexel(page: Int, per_page: Int) = apiHelper.getDataPexel(page, per_page)
    suspend fun searchPexel(textQuery: String, per_page: Int) =
        apiHelper.searchData(textQuery, per_page)
}