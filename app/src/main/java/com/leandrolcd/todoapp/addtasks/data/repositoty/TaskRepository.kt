package com.leandrolcd.todoapp.addtasks.data.repositoty


import com.leandrolcd.todoapp.addtasks.data.room.entity.TaskDao
import com.leandrolcd.todoapp.addtasks.data.room.entity.TaskEntity
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val task: Flow<List<TaskModel>> = taskDao.getAllTasks().map { items ->
        items.map { TaskModel(it.id, it.task, it.selected)
        }
    }

    suspend fun add(model: TaskModel) {
        taskDao.addTask(TaskEntity(id = model.id, task = model.task, selected = model.selected))
    }

    suspend fun update(model: TaskModel) {
        taskDao.upDateTask(TaskEntity(id = model.id, task = model.task, selected = model.selected))
    }

     suspend fun delete(model: TaskModel) {
         taskDao.deleteTask(TaskEntity(id = model.id, task = model.task, selected = model.selected))
    }
}