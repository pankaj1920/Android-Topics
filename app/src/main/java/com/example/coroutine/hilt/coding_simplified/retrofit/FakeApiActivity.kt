package com.example.coroutine.hilt.coding_simplified.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.coroutine.Print
import com.example.coroutine.R
import com.example.coroutine.hilt.coding_simplified.retrofit.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FakeApiActivity : AppCompatActivity() {
    private val postViewModel:PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_api)

        postViewModel.response.observe(this, Observer {
            Print.log("Post => ${it[0].body }")
        })
    }
}