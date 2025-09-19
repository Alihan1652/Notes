package com.example.notes.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_list")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val desc: String,
    val createdAt: Long = System.currentTimeMillis() // время создания
)
