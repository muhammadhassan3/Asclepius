package com.dicoding.asclepius.data.remote.response

data class ArticlesResponse(val status: String, val totalResults: Int, val articles: List<Article>)
