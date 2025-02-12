package com.example.taskb.network


import com.example.taskb.network.model.ImageResponse
import retrofit2.http.GET


interface ApiService {
    @GET("photos")
    suspend fun getImages(): ImageResponse
}

