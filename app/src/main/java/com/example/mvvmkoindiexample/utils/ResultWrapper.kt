package com.example.mvvmkoindiexample.utils

import okhttp3.ResponseBody

sealed class ResultWrapper<out T> {
    data class Success<T>(var value : T) : ResultWrapper<T>()
    data class Failure(
        val isNetworkError: Boolean?,
        val errorCode: Int?,
        val errorBody: String?,
        val localizedError: String?
    ) : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
}
