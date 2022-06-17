package com.example.imagegallery.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrPhotoService {
    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&extras=url_s")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
        @Query("api_key") key: String
    ): Call<FlickrResponse>
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&extras=url_s")
    fun getSearchResult(
        @Query("text") query: String,
        @Query("api_key") key: String
    ): Call<FlickrResponse>
}