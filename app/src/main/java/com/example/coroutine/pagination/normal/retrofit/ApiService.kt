package com.example.coroutine.pagination.normal.retrofit

import com.example.coroutine.pagination.normal.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/quotes")
    suspend fun getQuote(@Query("page") page: Int): Response<QuoteList>

}