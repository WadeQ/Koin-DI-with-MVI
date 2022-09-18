package com.example.mvvmkoindiexample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.domain.repo.IPostsRepository
import com.example.mvvmkoindiexample.utils.PostsState
import com.example.mvvmkoindiexample.utils.ResultWrapper
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: IPostsRepository
) : ViewModel() {

    var state by mutableStateOf(PostsState())
        private set
    
    init {
        getAllPosts()
        getPostsFromDb()
    }

    private fun getAllPosts() = viewModelScope.launch {
        repository.getAllPosts().also {
            when (it) {
                is ResultWrapper.Success -> repository.savePostsToDb(it.value)
                is ResultWrapper.Failure -> {
                    state = state.copy(
                        posts = listOf(),
                        isLoading = false,
                        error = it.errorBody.toString()
                    )
                }
                else -> Unit
            }
        }
    }

    private fun getPostsFromDb() = viewModelScope.launch {
       state = state.copy(
           isLoading = true,
           error = null
       )
        repository.getPostsFromLocal().also { posts ->
            state = if (posts.isNotEmpty()) state.copy(posts = posts, isLoading = false, error = null)
            else state.copy(posts = listOf(), isLoading = false, error = "Error fetching posts from db..")
        }
    }
}