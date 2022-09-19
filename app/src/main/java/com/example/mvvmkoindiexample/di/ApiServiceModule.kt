package com.example.mvvmkoindiexample.di

import com.example.mvvmkoindiexample.data.remote.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServiceModule = module {
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { providesApiService(get()) }
}