package com.leandrolcd.todoapp.addtasks.data.room.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks():Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(item: TaskEntity)


    @Update
    suspend fun upDateTask(item: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}