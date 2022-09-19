package com.example.mvvmkoindiexample.di

import com.example.mvvmkoindiexample.data.local.mapper.LocalMapper
import com.example.mvvmkoindiexample.data.local.room.PostsDao
import com.example.mvvmkoindiexample.data.remote.api.ApiService
import com.example.mvvmkoindiexample.data.remote.mapper.RemoteMapper
import com.example.mvvmkoindiexample.data.repository.PostsRepositoryImpl
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun providePostsRepository(
        api: ApiService,
        remoteMapper: RemoteMapper,
        localMapper: LocalMapper,
        dao: PostsDao
    ): IPostsRepository {
        return PostsRepositoryImpl(
            api,
            remoteMapper,
            localMapper,
            dao
        )
    }

    single { providePostsRepository(get(), get(), get(), get()) }
}