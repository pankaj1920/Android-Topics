package com.example.coroutine.hilt.coding_simplified.demo

import com.example.coroutine.Print
import javax.inject.Inject

class Car {
    @Inject
    constructor()

    fun getCar() {
        Print.log("car is running")
    }
}