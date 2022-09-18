package com.example.mvvmkoindiexample.di

import com.example.mvvmkoindiexample.data.remote.api.ApiService
import com.example.mvvmkoindiexample.data.repository.PostsRepositoryImpl
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import com.example.mvvmkoindiexample.presentation.viewmodels.PostsViewModel
import com.example.mvvmkoindiexample.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<IPostsRepository> {
        PostsRepositoryImpl(get())
    }

    viewModel {
        PostsViewModel(get())
    }
}