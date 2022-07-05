package com.aneeb.sadapayhomeexercize.network

import com.aneeb.sadapayhomeexercize.model.TrendingRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitService {

    @GET("search/repositories?q=language=+sort:stars")
    fun getTrendingRepositories(): Call<TrendingRepository>


    companion object {

        private const val BASE_URL = "https://api.github.com/"

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}