package com.example.user.di

import com.example.user.R
import com.example.user.data.DefaultMainScreenRepository

import com.example.user.data.MainScreenRepository
import com.example.user.data.remote.MainScreenApi
import com.example.user.main.MainViewModel
import com.example.user.network.RetrofitManager
import com.example.user.ui.detail_screen.DetailScreenViewModel
import com.example.user.ui.main_screen.MainScreenViewModel
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val systemModule = module {
    single { GsonBuilder().create() }
    single { RetrofitManager(androidContext().getString(R.string.base_url), get()) }
}

val mainModule = module {
    viewModel { MainViewModel() }
}


val mainScreenModule = module {
    single { get<RetrofitManager>().getService(MainScreenApi::class.java) }
    single<MainScreenRepository> { DefaultMainScreenRepository(get()) }

    viewModel { MainScreenViewModel(get()) }

}

val detailScreenModule = module {
    viewModel { DetailScreenViewModel() }

}

