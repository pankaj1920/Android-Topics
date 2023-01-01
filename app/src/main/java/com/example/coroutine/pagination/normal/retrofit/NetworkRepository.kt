package com.example.coroutine.pagination.normal.retrofit

import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.network_base.NetworkBoundRepository
import com.applaunch.bgm.network.RetrofitClient
import com.example.coroutine.pagination.normal.model.QuoteList

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NetworkRepository(private val baseRepoListener: BaseRepoListener) {
    private val apiService = RetrofitClient(baseRepoListener)

    fun getQuote(pageCount:Int): Flow<State<QuoteList>> {
        return object : NetworkBoundRepository<QuoteList>(baseRepoListener) {
            override suspend fun fetchData(): Response<QuoteList> {
                return apiService.getApiService().getQuote(pageCount)
            }
        }.asFlow()
    }

}