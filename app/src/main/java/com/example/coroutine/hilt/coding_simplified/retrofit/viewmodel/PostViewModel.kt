package com.example.coroutine.hilt.coding_simplified.retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.coroutine.Print
import com.example.coroutine.hilt.coding_simplified.retrofit.model.Post
import com.example.coroutine.hilt.coding_simplified.retrofit.repository.FakeApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val fakeApiRepository: FakeApiRepository) :
    ViewModel() {

    val response: LiveData<List<Post>> = fakeApiRepository.getPost().catch { e ->
        Print.log(e.message.toString())
    }.asLiveData()
}