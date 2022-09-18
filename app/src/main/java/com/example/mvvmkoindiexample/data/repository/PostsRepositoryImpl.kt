package com.example.mvvmkoindiexample.data.repository

import com.example.mvvmkoindiexample.data.local.room.PostsDao
import com.example.mvvmkoindiexample.data.local.mapper.LocalMapper
import com.example.mvvmkoindiexample.data.remote.api.ApiService
import com.example.mvvmkoindiexample.data.remote.mapper.RemoteMapper
import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import com.example.mvvmkoindiexample.utils.ResultWrapper
import timber.log.Timber


class PostsRepositoryImpl(
    private val apiService: ApiService,
    private val remoteMapper: RemoteMapper,
    private val localMapper: LocalMapper,
    private val postsDao: PostsDao
) : IPostsRepository {

    override suspend fun getAllPosts(): ResultWrapper<List<Posts>> {
        return try {
           ResultWrapper.Success(
               value = remoteMapper.mapToDomainList(apiService.getAllPostsAsync())
           )
        } catch (e: Exception){
            Timber.d("Error fetching posts with ${e.printStackTrace()}")
            ResultWrapper.Failure(
                false,
                null,
                e.message,
                e.localizedMessage
            )
        }
    }

    override suspend fun getPostsFromLocal(): List<Posts> {
        return localMapper.mapFromEntityList(postsDao.getAllPosts())
    }

    override suspend fun savePostsToDb(posts: List<Posts>) {
        postsDao.saveAllPosts(localMapper.mapToEntityList(posts))
    }
}