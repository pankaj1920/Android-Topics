package com.example.coroutine.pagination.normal.viewmodel

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.example.paginglibarary.model.Result
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.example.coroutine.pagination.normal.adapter.NormalPagingAdapter
import com.example.coroutine.pagination.normal.retrofit.NetworkRepository
import com.example.coroutine.pagination.normal.state.QuoteState
import doNothing
import kotlinx.coroutines.launch

class QuoteViewModel : BaseViewModel<QuoteState>() {

    lateinit var quoteAdapter:NormalPagingAdapter

    var quoteFragmentState: QuoteState = QuoteState.Init
        set(value) {
            field = value
            publishState(quoteFragmentState)
        }

    override fun onInitialized(bundle: Bundle?) {

    }

    fun getQuote(pageCount: Int, baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).getQuote(pageCount).collect {
                when (it) {
                    is State.Success -> {
                        Print.log("home Success Response ${it.data}")
                       quoteFragmentState = QuoteState.QuoteSuccess(it.data.results)
                    }
                    is State.Error -> {
                        Print.log("home Error Response ${it.message}")
                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun initQuote(dataList: ArrayList<Result>){
        quoteAdapter = NormalPagingAdapter(dataList, this)
        quoteFragmentState = QuoteState.QuoteList(quoteAdapter)
    }
}