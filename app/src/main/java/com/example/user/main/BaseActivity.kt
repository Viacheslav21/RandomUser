package com.example.user.main


import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


abstract class BaseActivity<T : BaseViewModel>(@LayoutRes val contentLayout: Int) :
    AppCompatActivity(contentLayout), BaseView<T> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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