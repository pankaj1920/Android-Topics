package com.example.coroutine.cheezey_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.Print
import com.example.coroutine.R
import kotlinx.coroutines.*

class LaunchAsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_async)

       CoroutineScope(Dispatchers.IO).launch {
            printFollower()
            printLike()
            printData()
           Print.log("Fb===============")
        }


        Print.log("OncreTE+++++++++++++++")

    }

    private suspend fun printData() {
        CoroutineScope(Dispatchers.IO).launch {
            var fbFollower = async { getFbFollower() }
            var instaFollower = async { getInstaFollower() }

            Print.log("Fb Follower ${fbFollower.await()} and Insta follower ${instaFollower.await()}")
        }
    }

    private suspend fun printLike() {
        var fbLike = 0
        var instaLike = 0
        val job = CoroutineScope(Dispatchers.IO).async {
            getFbFollower()
        }
        val job2 = CoroutineScope(Dispatchers.IO).async {
            getInstaFollower()
        }
        fbLike = job.await()
        instaLike = job2.await()
        Print.log("Fb Like => ${fbLike} Insta Like => ${instaLike}")
        Print.log("Fb Like => sdffdfdsfdsf")
    }

    private suspend fun printFollower() {
        var fbFollower = 0
        var instaFollower = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollower = getFbFollower()
        }

        val job2 = CoroutineScope(Dispatchers.IO).launch {
            instaFollower = getInstaFollower()
        }


        job.join()
        job2.join()
        Print.log("Fb Follower => $fbFollower Insta Follower => $instaFollower")
    }

    private suspend fun getFbFollower(): Int {
        delay(1000)
        return 54
    }

    private suspend fun getInstaFollower(): Int {
        delay(1000)
        return 113
    }


}