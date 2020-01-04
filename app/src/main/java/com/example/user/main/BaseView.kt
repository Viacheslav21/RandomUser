package com.example.user.main

interface BaseView<T: BaseViewModel> {
    val viewModel: T

    fun subscribeToEvents()

    fun handleError(throwable: Throwable)

    fun showLoading(isLoading: Boolean)
}