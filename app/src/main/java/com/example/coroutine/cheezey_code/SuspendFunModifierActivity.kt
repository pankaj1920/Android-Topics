package com.example.coroutine.cheezey_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.Print
import com.example.coroutine.databinding.ActivitySuspendFunModifierBinding
import kotlinx.coroutines.*

class SuspendFunModifierActivity : AppCompatActivity() {
    private val TAG = "TAG"
    lateinit var binding: ActivitySuspendFunModifierBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuspendFunModifierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTask1.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                taskOne()
            }

            CoroutineScope(Dispatchers.Main).launch {
                taskTwo()
            }
        }

        binding.btnTask2.setOnClickListener {

        }

    }

    suspend fun taskOne() {
        Print.log("STARTING TASK 1")
        delay(100)
        Print.log("ENDING TASK 1")
    }

    suspend fun taskTwo() {
        Print.log("STARTING TASK 2")
        delay(1000)
        Print.log("ENDING TASK 2")
    }
}