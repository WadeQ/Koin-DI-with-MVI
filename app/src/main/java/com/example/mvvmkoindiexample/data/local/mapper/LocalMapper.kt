package com.example.mvvmkoindiexample.data.local.mapper

import com.example.mvvmkoindiexample.domain.pojo.Posts
import com.example.mvvmkoindiexample.utils.PojoMapper

class LocalMapper() : PojoMapper<LocalPosts, Posts> {

    override fun mapToDomain(entity: LocalPosts): Posts {
        return Posts(
            id = entity.id,
            userId = entity.userId,
            body = entity.body,
            title = entity.title
        )
    }

    override fun mapToEntity(domain: Posts): LocalPosts {
       return LocalPosts(
           id = domain.id,
           userId = domain.userId,
           body = domain.body,
           title = domain.title
       )
    }

    fun mapFromEntityList(list: List<LocalPosts>) : List<Posts>{
        return list.map { mapToDomain(it) }
    }

    fun mapToEntityList(list: List<Posts>) : List<LocalPosts> {
        return list.map {
            mapToEntity(it)
        }
    }
}