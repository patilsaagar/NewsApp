package com.example.newsapp.Utils

import com.example.newsapp.Model.Source

sealed class ApiState {

    class Success(val newsData: List<Source>) : ApiState()
    class Failure(val throwable: Throwable) : ApiState()
    object Loading: ApiState()
    object Empty: ApiState()
}
