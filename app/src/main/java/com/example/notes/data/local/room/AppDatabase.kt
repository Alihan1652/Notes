package com.example.notes.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.local.models.TaskModel

@Database(entities = [TaskModel::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): TaskDao
}