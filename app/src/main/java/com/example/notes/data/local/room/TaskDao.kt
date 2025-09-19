package com.example.notes.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.data.local.models.TaskModel
import java.util.ArrayList

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks_list")
    fun getAllTask(): List<TaskModel>

    @Insert
    fun addTask(taskModel: TaskModel)

    @Delete
    fun deleteTask(task: TaskModel)
}