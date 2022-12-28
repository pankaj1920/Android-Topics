package com.example.coroutine.cheezey_code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.Print
import com.example.coroutine.R
import kotlinx.coroutines.*

class JobCancellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_cancellation)

           GlobalScope.launch(Dispatchers.Main) {
               executeLongTask()
           }


    }

    private suspend fun execute() {
        Print.log("Parent Started")
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
//             Print.log("Parent Coroutine Context $coroutineContext")

            val childJob = launch {
//                 Print.log("Child Coroutine Context $coroutineContext")

                Print.log("Child Started")
                delay(5000)
                Print.log("Child Ended")

            }

            delay(3000)
            Print.log("Parent End")
        }
        Print.log("XXXXXXXXXXXXXx")
        delay(1000)
        parentJob.cancel()
        parentJob.join()

        Print.log("Parent Completed")
    }

    private suspend fun executeLongTask() {
        val parentJob = GlobalScope.launch(Dispatchers.IO) {
            for (i in 1..10000) {
                if(isActive){
                    longRunningTask()
                    Print.log("Print $i")
                }

            }

        }

        delay(100)
        Print.log("Cancelling Job")
        parentJob.cancel()
        parentJob.join()
        Print.log("Parent Job Completed")
    }


    private fun longRunningTask() {
        for (i in 1..100000000) {

        }
    }
}