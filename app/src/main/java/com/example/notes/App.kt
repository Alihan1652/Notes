package com.example.notes

import android.app.Application
import androidx.room.Room
import com.example.notes.data.local.room.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,"database_task")
            .allowMainThreadQueries()
            .build()
    }
    companion object{
        lateinit var db: AppDatabase
    }
}