package com.example.newsapp.Repository

import com.example.newsapp.RetroFit.APIService
import com.example.newsapp.Utils.NewsAPIHelper
import com.example.newsapp.Model.NewsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NewsRepository @Inject constructor(private val apiService:APIService) {

    private lateinit var newsCategory: String

    fun setNewsCategory(newsCategory: String) {
        this.newsCategory = newsCategory
    }

    fun fetchNewsData(): Flow<NewsDetails> = flow {
        kotlinx.coroutines.delay(1_000)
        emit(apiService.fetchNewsData(apiKey = NewsAPIHelper.API_KEY, newsCategory = newsCategory))
    }.flowOn(Dispatchers.IO)
}