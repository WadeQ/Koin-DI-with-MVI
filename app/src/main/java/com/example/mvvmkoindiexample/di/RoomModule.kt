package com.example.mvvmkoindiexample.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmkoindiexample.data.local.room.PostsDao
import com.example.mvvmkoindiexample.data.local.room.PostsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {

    fun providesRoomDatabase(application: Application): PostsDatabase {
        return Room.databaseBuilder(application, PostsDatabase::class.java, PostsDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providesPostsDao(database: PostsDatabase): PostsDao {
        return  database.postsDao()
    }

    single { providesRoomDatabase(androidApplication()) }
    single { providesPostsDao(get()) }

}