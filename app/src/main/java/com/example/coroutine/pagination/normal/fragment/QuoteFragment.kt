package com.example.coroutine.pagination.normal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginglibarary.model.Result
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.appbase.view_base.BaseFragment
import com.example.coroutine.Print
import com.example.coroutine.R
import com.example.coroutine.databinding.FragmentQuoteBinding
import com.example.coroutine.pagination.normal.adapter.NormalPagingAdapter
import com.example.coroutine.pagination.normal.state.QuoteState
import com.example.coroutine.pagination.normal.viewmodel.QuoteViewModel


class QuoteFragment : BaseFragment<QuoteViewModel, FragmentQuoteBinding>() {

    lateinit var list: ArrayList<Result>
    lateinit var adapter: NormalPagingAdapter
    private var PAGE_COUNT = 1
    private var MAX_PAGE_COUNT = 1
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0


    override val mViewModel: QuoteViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_quote

    override fun onFragmentCreated(isBindingExist: Boolean) {
        mViewModel.getQuote(0, iBaseRepoListener)

        list = ArrayList()
        initReportSlider()

    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is QuoteState.QuoteList -> {
                    mViewBinding.quoteRecycler.adapter = it.quoteAdapter
                }

                is QuoteState.QuoteSuccess -> {
                    mViewModel.initQuote(it.dataList)
                }
                else -> {}
            }
        }
    }


    private fun initReportSlider() {
        adapter = NormalPagingAdapter(list, mViewModel)
        val mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mViewBinding.quoteRecycler.setItemAnimator(DefaultItemAnimator())
        mViewBinding.quoteRecycler.setAdapter(adapter)
        mViewBinding.quoteRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading && visibleItemCount + pastVisiblesItems >= totalItemCount) {
                        loading = false
                        if (PAGE_COUNT < MAX_PAGE_COUNT) {
                            Print.log("MAX_PAGE_COUNT ==>> ${PAGE_COUNT}")
                            PAGE_COUNT++
//                            loadMoreLoader(true)
                            mViewModel.getQuote(PAGE_COUNT, iBaseRepoListener)
                        }
//                        loading = true
                    }
                }
            }
        })
    }

}