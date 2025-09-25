package com.example.notes.data.local.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel (private val repository: TaskRepository) : ViewModel() {
    fun getAllNotes(onResult: (List<TaskModel>) -> Unit) {
        viewModelScope.launch {
            val notes = withContext(Dispatchers.IO) {
                repository.getAllNotes()
            }
            onResult(notes)
        }
    }

    fun insert(note: TaskModel, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
            onComplete()
        }
    }

    fun delete(note: TaskModel, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
            onComplete()
        }
    }

    fun filterByDate(start: Long, end: Long, onResult: (List<TaskModel>) -> Unit) {
        viewModelScope.launch {
            val notes = withContext(Dispatchers.IO) {
                repository.getNotesByDate(start, end)
            }
            onResult(notes)
        }
    }
}