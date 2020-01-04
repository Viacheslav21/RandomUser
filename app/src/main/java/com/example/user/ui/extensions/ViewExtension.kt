package com.example.user.ui.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setUpPagination(onNextPage: () -> Unit) {
    val layoutManager = layoutManager
    if (layoutManager is LinearLayoutManager) {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var previousTotal = 0
            var loading = true
            var visibleThreshold = 0
            var firstVisibleItem = 0
            var visibleItemCount = 0
            var totalItemCount = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (visibleThreshold == 0) {
                    visibleThreshold = (totalItemCount * 30) / 100
                }

                if (loading) {
                    if (totalItemCount != previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    onNextPage()
                    loading = true
                }
            }
        })
    }
}