package com.example.coroutine.pagination.normal.model

import com.example.paginglibarary.model.Result

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: ArrayList<Result>,
    val totalCount: Int,
    val totalPages: Int
)