package com.leandrolcd.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor():ViewModel(){
    //region Fields
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog:LiveData<Boolean> = _showDialog

    private val _taskList = mutableStateListOf<TaskModel>()
    val taskList:List<TaskModel> = _taskList
    //endregion

//regions Methods
    fun onShowDialog() {
    _showDialog.value = true
    }
    fun onCloseDialog(){
        _showDialog.value = false
    }
    fun onTaskCrete(task: String) {
        _showDialog.value = false
        _taskList.add(TaskModel(task = task))
    }

    fun onItemChanged(item: TaskModel) {
        val index = _taskList.indexOf(item)
        _taskList[index] = _taskList[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onRemoveIteM(item: TaskModel) {
        val task = _taskList.find { it.id == item.id }
        _taskList.remove(task)
    }


//endregion
}