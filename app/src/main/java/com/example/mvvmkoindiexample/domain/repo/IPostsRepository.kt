package com.example.mvvmkoindiexample.domain.repo

import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.utils.ResultWrapper
import retrofit2.Response

interface IPostsRepository {

    suspend fun getAllPosts() : ResultWrapper<List<Posts>>

    suspend fun getPostsFromLocal() : List<Posts>

    suspend fun savePostsToDb(posts: List<Posts>)
}