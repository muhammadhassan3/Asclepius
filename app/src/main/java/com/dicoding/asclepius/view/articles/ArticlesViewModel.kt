package com.dicoding.asclepius.view.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.remote.response.Article
import com.dicoding.asclepius.data.remote.response.ArticlesResponse
import com.dicoding.asclepius.data.remote.response.UiState
import com.dicoding.asclepius.data.remote.retrofit.ApiClient
import com.dicoding.asclepius.data.remote.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesViewModel : ViewModel() {
    private val api = ApiClient.getInstance().create(ApiInterface::class.java)

    private val _data = MutableLiveData<UiState<List<Article>>>()
    val data: LiveData<UiState<List<Article>>> get() = _data

    init {
        getData()
    }

    private fun getData() {
        _data.value = UiState.Loading
        api.getArticles().enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>, response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful) {
                    _data.postValue(UiState.Success(response.body()?.articles ?: emptyList()))
                } else {
                    _data.postValue(UiState.Error("Gagal memuat data : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                _data.postValue(UiState.Error("Koneksi tidak berhasil"))
            }

        })
    }
}