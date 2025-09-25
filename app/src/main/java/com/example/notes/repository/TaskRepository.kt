package com.example.notes.repository

import com.example.notes.data.local.models.TaskModel
import com.example.notes.data.local.room.TaskDao

class TaskRepository (private val dao: TaskDao){
    fun getAllNotes(): List<TaskModel> = dao.getAllTask()
    fun insert(note: TaskModel) = dao.addTask(note)
    fun delete(note: TaskModel) = dao.deleteTask(note)
    fun update(note: TaskModel) = dao.updateNote(note)
    fun getNotesByDate(start: Long, end: Long): List<TaskModel> =
        dao.getNotesByDate(start, end)
}