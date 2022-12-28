package com.example.coroutine.u4universe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivityBocklingVsSuspendingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BocklingVsSuspendingActivity : AppCompatActivity() {
    lateinit var binding: ActivityBocklingVsSuspendingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBocklingVsSuspendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRunCode.setOnClickListener {
            CoroutineScope(Dispatchers.Main.immediate).launch {
                delay(3000)
                showMessage()
            }

        }
    }

    private fun showMessage() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
    }
}