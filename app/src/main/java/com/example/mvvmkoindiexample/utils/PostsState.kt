package com.example.mvvmkoindiexample.utils

import com.example.mvvmkoindiexample.domain.pojo.Posts

data class PostsState(
    val posts: List<Posts>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
