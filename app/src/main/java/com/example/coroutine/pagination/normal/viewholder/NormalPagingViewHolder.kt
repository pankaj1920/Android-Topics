package com.example.coroutine.pagination.normal.viewholder

import com.applaunch.appbase.adapter_base.BaseViewHolder
import com.example.coroutine.databinding.ItemQuoteLayoutBinding
import com.example.coroutine.pagination.normal.viewmodel.QuoteViewModel
import  com.example.paginglibarary.model.Result

class NormalPagingViewHolder(
    var binding: ItemQuoteLayoutBinding,
    var viewModel: QuoteViewModel
) : BaseViewHolder<Result, ItemQuoteLayoutBinding>(binding) {
    override fun populateData(data: Result, position: Int) {
        binding.quote.text = data.content
    }

}