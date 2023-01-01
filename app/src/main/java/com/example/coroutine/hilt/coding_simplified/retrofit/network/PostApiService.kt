package com.example.coroutine.hilt.coding_simplified.retrofit.network

import com.example.coroutine.hilt.coding_simplified.retrofit.model.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>
}