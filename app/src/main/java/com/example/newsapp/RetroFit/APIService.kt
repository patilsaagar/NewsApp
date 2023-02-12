package com.example.newsapp.RetroFit

import com.example.newsapp.Model.NewsDetails
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIService {

    @GET("/v2/top-headlines/sources")
    suspend fun fetchNewsData(
        @Header("Authorization") apiKey: String,
        @Query("category") newsCategory: String
    ): NewsDetails
}