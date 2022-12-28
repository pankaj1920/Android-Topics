package com.example.coroutine.hilt.coding_simplified

import com.example.coroutine.Print
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

class QualiferAnotation @Inject constructor(
    @FName
    private val fName: String,
    @LName
    private val lName: String) {

    fun getName() {
        Print.log("My name is $fName $lName")
    }
}


@Module
@InstallIn(SingletonComponent::class)
class ModuleApp{

    @Provides
    @FName
    fun getFirstName():String = "Pankaj"

    @Provides
    @LName
    fun getLastName():String = "Bohra"
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LName