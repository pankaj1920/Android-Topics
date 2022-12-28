package com.example.coroutine.hilt.coding_simplified.demo

import com.example.coroutine.Print
import javax.inject.Inject

class Engine {
    @Inject
    constructor()

    fun getEngine() {
        Print.log("Engine Started ....")
    }
}