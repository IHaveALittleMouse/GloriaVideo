package com.gloria.common.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VideoApi {
    @GET("api/invoke/video/invoke/video")
    suspend fun requestData(): List<VideoBean>

    companion object {
        private const val BASE_URL = "https://beiyou.bytedance.com/"

        fun createVideoApi(): VideoApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VideoApi::class.java)
        }
    }
}