package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.data.remote.response.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("v2/everything?q=cancer&pageSize=100")
    fun getArticles(): Call<ArticlesResponse>
}