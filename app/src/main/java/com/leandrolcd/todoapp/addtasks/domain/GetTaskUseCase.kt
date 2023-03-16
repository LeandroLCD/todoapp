package com.leandrolcd.todoapp.addtasks.domain

import com.leandrolcd.todoapp.addtasks.data.repositoty.TaskRepository
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke():Flow<List<TaskModel>> = repository.task

}