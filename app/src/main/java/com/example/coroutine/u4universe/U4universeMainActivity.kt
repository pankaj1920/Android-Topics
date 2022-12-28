package com.example.coroutine.u4universe

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.coroutine.Print
import com.example.coroutine.R
import com.example.coroutine.databinding.ActivityU4universeMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class U4universeMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityU4universeMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityU4universeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val url =
                    URL("https://cdn.pixabay.com/photo/2022/12/06/06/21/lavender-7638368_960_720.jpg")
                val bitmap = BitmapFactory.decodeStream(url.openStream())
                withContext(Dispatchers.Main) {
                    Print.log("Image Loading")  //2
                    binding.ivImage.setImageBitmap(bitmap)
                    Print.log("Image Succes") //3
                }

                Print.log("######################")//4
            }


            Print.log("XXXXXXXXXXXXXXXXXXXXXXXXXXX ") //1
        }
    }
}