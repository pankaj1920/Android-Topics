package com.example.coroutine.hilt.coding_simplified.demo

import com.example.coroutine.Print
import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine, private val wheel: Wheel) {


    fun getCar() {
        engine.getEngine()
        wheel.getWheel()
        Print.log("car is running")
    }
}