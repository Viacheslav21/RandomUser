package com.example.user.ui

import com.example.user.R
import com.example.user.main.BaseActivity
import com.example.user.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity: BaseActivity<MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()

}
