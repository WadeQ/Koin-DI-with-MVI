package com.example.mvvmkoindiexample.application

import android.app.Application
import com.example.mvvmkoindiexample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import timber.log.Timber

class PostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PostsApplication)
            val modules = mutableListOf<Module>().apply {
                addAll(appModules)
                addAll(roomModules)
                addAll(networkModules)
            }
            modules(modules)
        }

        Timber.plant(Timber.DebugTree())
    }
}