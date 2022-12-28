package com.example.coroutine

import android.util.Log

object Print {
    fun log(message:String){
        println("Print => $message")
        Log.e("Print","Print => $message")
    }
}