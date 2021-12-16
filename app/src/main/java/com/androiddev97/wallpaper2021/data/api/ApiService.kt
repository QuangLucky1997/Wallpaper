package com.androiddev97.wallpaper2021.data.api

import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/photos/")
    suspend fun getListPictures(
        @Query("client_id") clientId: String,
        @Query("page") pade: Int,
        @Query("per_page") page: Int
    ): List<ReponseUnplash>


}