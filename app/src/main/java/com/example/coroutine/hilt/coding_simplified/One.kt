package com.example.coroutine.hilt.coding_simplified

import com.example.coroutine.Print
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface One {
    fun getName()
}

class ImplementOne @Inject constructor():One{
    override fun getName() {
        Print.log("My name is Pankaj")
    }

}

class Main @Inject constructor(private val one:One){
    fun printName(){
        one.getName()
    }
}

/*
@Module
@InstallIn(SingletonComponent::class)
abstract  class AppModule{

    @Binds
    @Singleton
    abstract fun binding(
        implementOne: ImplementOne
    ):One
}*/


@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Provides
    @Singleton
    fun binding():One = ImplementOne()
}