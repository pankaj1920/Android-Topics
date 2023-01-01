package com.example.coroutine.hilt.coding_simplified.retrofit.network

import com.example.coroutine.hilt.coding_simplified.retrofit.model.Post
import javax.inject.Inject

class PostServiceImp @Inject constructor(private val postApiService: PostApiService) {

    suspend fun getPostList(): List<Post> = postApiService.getPost()
}