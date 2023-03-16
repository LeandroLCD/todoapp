package com.leandrolcd.todoapp.addtasks.data.room.entity

import androidx.room.PrimaryKey

data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    var selected: Boolean = false
)
