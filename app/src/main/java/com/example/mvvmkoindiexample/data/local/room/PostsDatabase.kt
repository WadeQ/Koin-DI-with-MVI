package com.example.mvvmkoindiexample.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmkoindiexample.data.local.mapper.LocalPosts

@Database(entities = [LocalPosts::class], version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postsDao() : PostsDao

    companion object{
        const val DATABASE_NAME : String = "ROOM_DB"
    }
}