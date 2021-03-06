package com.example.imagegallery.viewmodel

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagegallery.model.network.*
import com.example.imagegallery.model.storage.Photo
import com.example.imagegallery.model.storage.PhotoDao
import com.example.imagegallery.model.storage.PhotoRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var flickrResponse: MutableLiveData<FlickrResponse>
    lateinit var isLoading: MutableLiveData<Boolean>
    lateinit var error: MutableLiveData<Boolean>
    lateinit var PAGE_NO: MutableLiveData<Int>
    private var PAGE_SIZE = 20

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

    fun getFlickrResponse(): MutableLiveData<FlickrResponse> {
        if (!::flickrResponse.isInitialized) {
            flickrResponse = MutableLiveData()
            isLoading = MutableLiveData()
            isLoading.value = false
            error = MutableLiveData()
            PAGE_NO = MutableLiveData()
            PAGE_NO.value = 1
            fetchFlickrResponse(PAGE_NO.value!!)
        }

        return flickrResponse
    }

    fun refresh() {
        PAGE_NO.value = 1
        fetchFlickrResponse(PAGE_NO.value!!)
    }

    fun loadNextPage() {
        if (!(isLoading.value!!)) {
            PAGE_NO.value = PAGE_NO.value!! + 1
            fetchFlickrResponse(PAGE_NO.value!!)
        }
    }

    private fun fetchFlickrResponse(page: Int) {
        Log.i(TAG, "Page No: $page")
        isLoading.value = true
        error.value = false

        val flickrPhotoService = FlickrPhotoServiceBuilder.buildService(FlickrPhotoService::class.java)

        val response = flickrPhotoService.getPhotos(page, PAGE_SIZE, "6f102c62f41998d151e5a1b48713cf13")

        response.enqueue(object : Callback<FlickrResponse> {
            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                isLoading.value = false
                error.value = true
            }

            override fun onResponse(call: Call<FlickrResponse>, response: Response<FlickrResponse>) {
                isLoading.value = false
                if (response.isSuccessful) {
                    flickrResponse.value = response.body()
                }else{
                    error.value = true
                }
            }
        })
    }
}