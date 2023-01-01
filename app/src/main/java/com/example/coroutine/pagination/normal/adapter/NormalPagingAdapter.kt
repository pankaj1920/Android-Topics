package com.example.coroutine.pagination.normal.adapter

import android.view.ViewGroup
import com.applaunch.appbase.adapter_base.BaseRecyclerAdapter
import com.example.coroutine.R
import com.example.coroutine.databinding.ItemQuoteLayoutBinding
import com.example.coroutine.pagination.normal.viewholder.NormalPagingViewHolder
import com.example.coroutine.pagination.normal.viewmodel.PaginationViewModel
import com.example.coroutine.pagination.normal.viewmodel.QuoteViewModel
import com.example.paginglibarary.model.Result

class NormalPagingAdapter(
    list: MutableList<Result>,
    var quoteViewModel: QuoteViewModel,
) : BaseRecyclerAdapter<Result, NormalPagingViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalPagingViewHolder {
        return NormalPagingViewHolder(
            inflateDataBinding(R.layout.item_quote_layout, parent) as ItemQuoteLayoutBinding,
            quoteViewModel
        )
    }
}