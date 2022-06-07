package com.androiddev97.wallpaper2021.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getDataPictures(client_id: String, page: Int, perPages: Int) =
        apiService.getListPictures(client_id, page, perPages)


    suspend fun getDataPexel(page: Int, perPages: Int) =
        apiService.getListPexel(page, perPages)


    suspend fun searchData(query_text: String, perPages: Int) =
        apiService.searchPictures(query_text, perPages)

}