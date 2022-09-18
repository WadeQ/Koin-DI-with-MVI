package com.example.mvvmkoindiexample.application

import android.app.Application
import com.example.mvvmkoindiexample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class PostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PostsApplication)
            modules(appModule)
        }

        Timber.plant(Timber.DebugTree())
    }
}