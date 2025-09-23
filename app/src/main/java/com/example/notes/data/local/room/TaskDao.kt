package com.example.notes.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.Note
import com.example.notes.data.local.models.TaskModel
import java.util.ArrayList

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks_list ORDER BY id DESC")
    fun getAllTask(): List<TaskModel>

    @Insert
    fun addTask(taskModel: TaskModel)

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Update
    fun updateNote(note: TaskModel)

    @Query("SELECT * FROM tasks_list WHERE createdAt BETWEEN :start AND :end ORDER BY createdAt DESC")
    fun getNotesByDate(start: Long, end: Long): List<TaskModel>
}