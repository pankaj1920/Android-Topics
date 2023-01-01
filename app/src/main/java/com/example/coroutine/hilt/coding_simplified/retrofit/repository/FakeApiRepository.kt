package com.example.coroutine.hilt.coding_simplified.retrofit.repository

import com.example.coroutine.hilt.coding_simplified.retrofit.model.Post
import com.example.coroutine.hilt.coding_simplified.retrofit.network.PostServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeApiRepository
@Inject
constructor(private val postServiceImp: PostServiceImp) {

    fun getPost(): Flow<List<Post>> = flow {
        val response = postServiceImp.getPostList()
        emit(response)
    }.flowOn(Dispatchers.IO)
}