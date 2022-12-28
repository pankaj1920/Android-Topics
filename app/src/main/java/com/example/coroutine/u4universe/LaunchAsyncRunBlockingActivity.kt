package com.example.coroutine.u4universe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivityLaunchAsyncRunBlockingBinding

class LaunchAsyncRunBlockingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLaunchAsyncRunBlockingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}