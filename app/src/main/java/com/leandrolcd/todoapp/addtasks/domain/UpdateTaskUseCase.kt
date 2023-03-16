package com.leandrolcd.todoapp.addtasks.domain

import com.leandrolcd.todoapp.addtasks.data.repositoty.TaskRepository
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private var repository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) =  repository.update(taskModel)

}