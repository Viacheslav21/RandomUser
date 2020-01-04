package com.example.user.main

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<T : BaseViewModel>(@LayoutRes val layoutId: Int) : Fragment(layoutId),
    BaseView<T> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()
    }

    @CallSuper
    override fun subscribeToEvents() {
        viewModel.errorEvents.observe(this, Observer { handleError(it) })
        viewModel.loadingState.observe(this, Observer { showLoading(it) })
    }

    override fun handleError(throwable: Throwable) {}

    override fun showLoading(isLoading: Boolean) {}
}