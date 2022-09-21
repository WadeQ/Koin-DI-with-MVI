package com.example.mvvmkoindiexample.di

import android.app.Application
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.mvvmkoindiexample.BuildConfig
import com.example.mvvmkoindiexample.data.local.room.PostsDao
import com.example.mvvmkoindiexample.data.local.room.PostsDatabase
import com.example.mvvmkoindiexample.data.remote.api.ApiService
import com.example.mvvmkoindiexample.data.repository.PostsRepositoryImpl
import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import com.example.mvvmkoindiexample.presentation.viewmodels.PostsViewModel
import com.example.mvvmkoindiexample.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            PostsDatabase::class.java,
            PostsDatabase.DATABASE_NAME
        ).build()
    }
}

val daoModule: Module = module {
    single { get<PostsDatabase>().postsDao() }
}

val retrofitModule: Module = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        val chuckerCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        val chuckerInterceptor = ChuckerInterceptor.Builder(androidContext())
            .collector(chuckerCollector)
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}

val apiModule: Module = module {
    single<ApiService> { get<Retrofit>().create() }
}

val repositoryModule: Module = module {
    single<IPostsRepository> { PostsRepositoryImpl(get(), get(), get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { PostsViewModel(get()) }
}

val appModules: List<Module> = listOf(
    viewModelModule,
    repositoryModule
)

val roomModules: List<Module> = listOf(
    databaseModule,
    daoModule
)

val networkModules: List<Module> = listOf(
    retrofitModule,
    apiModule
)