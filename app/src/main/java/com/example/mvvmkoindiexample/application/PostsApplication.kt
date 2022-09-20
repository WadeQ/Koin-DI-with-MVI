package com.example.mvvmkoindiexample.application

import android.app.Application
import com.example.mvvmkoindiexample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class PostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PostsApplication)
            modules(
                retrofitModule,
                apiServiceModule,
                roomModule,
                repositoryModule,
                viewModelModule
            )
        }

        Timber.plant(Timber.DebugTree())
    }
}