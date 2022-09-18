package com.example.mvvmkoindiexample.data.remote.api

import com.example.mvvmkoindiexample.data.remote.mapper.RemotePostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getAllPostsAsync() : List<RemotePostsResponse>
}