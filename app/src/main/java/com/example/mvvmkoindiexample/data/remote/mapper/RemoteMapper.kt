package com.example.mvvmkoindiexample.data.remote.mapper

import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.utils.PojoMapper
import com.example.mvvmkoindiexample.utils.ResultWrapper

class RemoteMapper() : PojoMapper<RemotePostsResponse, Posts> {
    override fun mapToDomain(entity: RemotePostsResponse): Posts {
        return Posts(
            id = entity.id,
            userId = entity.userId,
            body = entity.body,
            title = entity.title
        )
    }

    override fun mapToEntity(domain: Posts): RemotePostsResponse {
        return RemotePostsResponse(
            id = domain.id,
            userId = domain.userId,
            body = domain.body,
            title = domain.title
        )
    }

    fun mapToDomainList(list: List<RemotePostsResponse>) : List<Posts>{
        return list.map {
            mapToDomain(it)
        }
    }
}