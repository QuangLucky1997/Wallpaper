package com.androiddev97.wallpaper2021.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getDataPictures(client_id: String,page:Int,perPages:Int) =
        apiService.getListPictures(client_id, page,perPages)


    suspend fun getDataPexel(page:Int,perPages:Int) =
        apiService.getListPexel( page,perPages)

}