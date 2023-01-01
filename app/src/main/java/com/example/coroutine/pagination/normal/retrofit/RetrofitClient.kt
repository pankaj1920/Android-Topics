package com.applaunch.bgm.network

import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.network_base.BaseRetrofitClient
import com.example.coroutine.pagination.normal.retrofit.ApiService
import com.example.coroutine.pagination.normal.retrofit.Constants

class RetrofitClient(repoListener: BaseRepoListener) : BaseRetrofitClient(repoListener) {
    fun getApiService(): ApiService =
        provideRetrofit(provideOkHttpClient(), Constants.BASE_URL).create(ApiService::class.java)
}