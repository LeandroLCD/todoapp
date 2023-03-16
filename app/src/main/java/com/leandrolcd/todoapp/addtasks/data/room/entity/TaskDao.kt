package com.leandrolcd.todoapp.addtasks.data.room.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks():Flow<List<TaskEntity>>

    @Insert
    suspend fun AddTask(item: TaskEntity)
}