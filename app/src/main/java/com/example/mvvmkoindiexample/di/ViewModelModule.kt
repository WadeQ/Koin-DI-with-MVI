package com.example.mvvmkoindiexample.di

import com.example.mvvmkoindiexample.presentation.viewmodels.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PostsViewModel(repository = get())
    }
}