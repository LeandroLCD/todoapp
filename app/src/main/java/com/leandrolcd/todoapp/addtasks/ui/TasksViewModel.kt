package com.leandrolcd.todoapp.addtasks.ui


import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leandrolcd.todoapp.addtasks.domain.AddTaskUseCase
import com.leandrolcd.todoapp.addtasks.domain.DeleteTaskUseCase
import com.leandrolcd.todoapp.addtasks.domain.GetTaskUseCase
import com.leandrolcd.todoapp.addtasks.domain.UpdateTaskUseCase
import com.leandrolcd.todoapp.addtasks.ui.TasksUiState.*
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Error
import kotlin.reflect.KFunction1
@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTaskUseCase: GetTaskUseCase,
) :ViewModel(){

    //region Fields
    val uiState: StateFlow<TasksUiState> =
        getTaskUseCase().map(::Success)
            .catch { Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)


    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _taskList = mutableStateListOf<TaskModel>()


    //val taskList: List<TaskModel> = _taskList
    //endregion

    //regions Methods
    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onCloseDialog() {
        _showDialog.value = false
    }

    fun onTaskCrete(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onItemChanged(taskModel: TaskModel) {

        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onRemoveIteM(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }


//endregion

}