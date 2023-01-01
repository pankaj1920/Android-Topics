package com.example.coroutine.pagination.normal.state

import com.example.coroutine.pagination.normal.adapter.NormalPagingAdapter
import com.example.paginglibarary.model.Result

sealed  class QuoteState {
    data class QuoteList(var quoteAdapter: NormalPagingAdapter) : QuoteState()
    data class QuoteSuccess(var dataList: ArrayList<Result>) : QuoteState()

    object Init:QuoteState()
}