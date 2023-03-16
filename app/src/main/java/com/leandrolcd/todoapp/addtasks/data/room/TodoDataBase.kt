package com.leandrolcd.todoapp.addtasks.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leandrolcd.todoapp.addtasks.data.room.entity.TaskDao
import com.leandrolcd.todoapp.addtasks.data.room.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDataBase:RoomDatabase() {
    abstract fun  taskDao(): TaskDao
}