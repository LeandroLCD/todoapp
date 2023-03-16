package com.leandrolcd.todoapp.addtasks.data.room.di

import android.content.Context
import androidx.room.Room
import com.leandrolcd.todoapp.addtasks.data.room.TodoDataBase
import com.leandrolcd.todoapp.addtasks.data.room.entity.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideTaskDao(todoDatabase: TodoDataBase):TaskDao{
        return todoDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDataBase {
        return Room.databaseBuilder(appContext, TodoDataBase::class.java, "TaskDatabase").build()
    }


}