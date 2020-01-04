package com.example.user.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    private val _errorEvents = SingleErrorLiveData()
    val errorEvents: LiveData<Throwable> = _errorEvents

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    protected fun handleError(throwable: Throwable?) {
        if (throwable != null) _errorEvents.postValue(throwable)
    }

    protected fun showLoading(isLoading: Boolean) {
        _loadingState.postValue(isLoading)
    }
}