package com.example.mvvmkoindiexample.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmkoindiexample.data.local.mapper.LocalMapper
import com.example.mvvmkoindiexample.data.local.room.PostsDao
import com.example.mvvmkoindiexample.data.local.room.PostsDatabase
import com.example.mvvmkoindiexample.data.remote.api.ApiService
import com.example.mvvmkoindiexample.data.remote.mapper.RemoteMapper
import com.example.mvvmkoindiexample.data.repository.PostsRepositoryImpl
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import com.example.mvvmkoindiexample.presentation.viewmodels.PostsViewModel
import com.example.mvvmkoindiexample.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory










