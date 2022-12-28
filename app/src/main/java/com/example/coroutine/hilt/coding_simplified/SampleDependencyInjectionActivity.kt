package com.example.coroutine.hilt.coding_simplified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivitySampleDependencyInjectionBinding
import com.example.coroutine.hilt.coding_simplified.demo.Car
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SampleDependencyInjectionActivity : AppCompatActivity() {
    @Inject
    lateinit var car:Car

    lateinit var binding:ActivitySampleDependencyInjectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleDependencyInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        car.getCar()

    }
}