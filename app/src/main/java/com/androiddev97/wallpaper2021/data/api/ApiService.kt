package com.androiddev97.wallpaper2021.data.api

import com.androiddev97.wallpaper2021.data.model.pexel.PexelReponse
import com.androiddev97.wallpaper2021.data.model.pexel.Photo
import com.androiddev97.wallpaper2021.data.model.unplash.ReponseUnplash
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("/photos/")
    suspend fun getListPictures(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ReponseUnplash>




    @Headers("Authorization: 563492ad6f917000010000016a8ac38d3ba743cbbaa2d91f024fd226")
    @GET("/v1/curated")
    suspend fun getListPexel(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PexelReponse


    @Headers("Authorization: 563492ad6f917000010000016a8ac38d3ba743cbbaa2d91f024fd226")
    @GET("/v1/curated")
    suspend fun searchPictures(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PexelReponse


}