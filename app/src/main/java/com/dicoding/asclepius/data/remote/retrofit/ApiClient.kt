package com.dicoding.asclepius.data.remote.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val client by lazy{
        OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", "410ba3e7ec4a43de9ad8afdd39de4dac")

            chain.proceed(request.build())
        }.build()
    }

    @JvmStatic
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit = INSTANCE ?: synchronized(this){
        val instance = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        INSTANCE = instance
        instance
    }
}