package com.example.coroutine.cheezey_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("TAG", "${Thread.currentThread().name}")
        binding.btnUpdate.setOnClickListener {
            updateCounter()
        }

        binding.btnExecuteTask.setOnClickListener {
            thread(start = true) {
                longRunningTask()
                doAction()
            }

        }
    }

    fun updateCounter() {
        Log.e("TAG", "${Thread.currentThread().name}")
        binding.tvCounter.text = "${binding.tvCounter.getText().toString().toInt() + 1}"
    }

    fun longRunningTask() {
        for (i in 1..1000000000000L) {

        }
    }

    fun doAction() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("TAG", "1 - ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.e("TAG", "2 - ${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Default) {
            Log.e("TAG", "3 - ${Thread.currentThread().name}")
        }
    }
}