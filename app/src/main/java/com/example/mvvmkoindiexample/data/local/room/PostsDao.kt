package com.example.mvvmkoindiexample.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmkoindiexample.data.local.mapper.LocalPosts

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts_db ORDER BY id ASC")
    fun getAllPosts(): List<LocalPosts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllPosts(posts: List<LocalPosts>)
}