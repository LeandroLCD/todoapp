package com.leandrolcd.todoapp.addtasks.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    var selected: Boolean = false
)
