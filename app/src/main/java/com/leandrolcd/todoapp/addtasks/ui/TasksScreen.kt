package com.leandrolcd.todoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.leandrolcd.todoapp.addtasks.ui.TasksUiState.*
import com.leandrolcd.todoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<TasksUiState>(
        initialValue = Loading,
        key1 = lifecycle,
        key2 = tasksViewModel) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            tasksViewModel.uiState.collect{value = it}
        }
    }

    when(uiState){
        is Error -> {

        }
        Loading -> {
            CircularProgressIndicator()
        }
        is Success -> {
            Box(Modifier.fillMaxSize()) {
                AddTasksDialog(
                    showDialog,
                    onDismiss = { tasksViewModel.onCloseDialog() },
                    onTaskAdd = { tasksViewModel.onTaskCrete(it) })
                FabDialog(
                    tasksViewModel,
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                )
                val list = (uiState as Success).tasks
                TasksList( list,tasksViewModel)

            }
        }

    }

}

@Composable
fun TasksList(list: List<TaskModel>, viewModel: TasksViewModel) {

    LazyColumn() {
        items(list, key = { it.id }) {
           ItemTask(item = it, viewModel = viewModel)
        }
    }
}


@Composable
fun ItemTask(item: TaskModel, viewModel: TasksViewModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    viewModel.onRemoveIteM(item)
                })
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 8.dp) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = item.task, modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
            )
            Checkbox(checked = item.selected, onCheckedChange = { viewModel.onItemChanged(item) })
        }
    }
}

@Composable
fun FabDialog(tasksViewModel: TasksViewModel, modifier: Modifier) {
    FloatingActionButton(onClick = { tasksViewModel.onShowDialog() }, modifier = modifier) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar Tarea")
    }
}

@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdd: (String) -> Unit) {
    var myTask by remember {
        mutableStateOf("")
    }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nueva Tarea", modifier = Modifier.align(Alignment.Center),
                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                    Icon(imageVector = Icons.Filled.Close,
                        contentDescription = "Close dialog",
                        modifier = Modifier
                            .align(
                                Alignment.TopEnd
                            )
                            .clickable { onDismiss() })
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        onTaskAdd(myTask)
                        myTask = ""
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Agregar")
                }
            }
        }
    }
}
