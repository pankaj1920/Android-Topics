package com.example.coroutine.hilt.coding_simplified.demo

import com.example.coroutine.Print
import javax.inject.Inject

class Wheel {
    @Inject
    constructor()

    fun getWheel(){
        Print.log("Wheel Started")
    }
}