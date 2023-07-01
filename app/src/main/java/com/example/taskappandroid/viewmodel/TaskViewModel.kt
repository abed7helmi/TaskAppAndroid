package com.example.taskappandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.viewModelScope

import com.example.taskappandroid.database.Task
import com.example.taskappandroid.database.TaskDatabase
import com.example.taskappandroid.repo.TaskRepo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch




class TaskViewModel(application: Application) : AndroidViewModel(application){
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepo
    val getAllTasks: Flow<List<Task>>
    init {
        repository = TaskRepo(taskDao)
        getAllTasks = repository.getAllTasks()
        Log.v("test",getAllTasks.toString())
    }

    fun insert(task : Task){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.insert(task)
        }
    }

    fun delete(task : Task){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteItem(task)
        }
    }

    fun deleteAll(){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.deleteAll()
        }
    }



}

