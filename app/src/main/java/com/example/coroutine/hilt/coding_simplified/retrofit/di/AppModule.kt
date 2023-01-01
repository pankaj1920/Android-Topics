package com.example.coroutine.hilt.coding_simplified.retrofit.di

import com.example.coroutine.hilt.coding_simplified.retrofit.network.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit.Builder().baseUrl(provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providePostApiService(retrofit: Retrofit): PostApiService =
        retrofit.create(PostApiService::class.java)
}