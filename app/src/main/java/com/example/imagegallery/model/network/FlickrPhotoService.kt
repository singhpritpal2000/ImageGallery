package com.example.imagegallery.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotoService {
    @GET("?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
        @Query("api_key") key: String
    ): Call<FlickrResponse>
}