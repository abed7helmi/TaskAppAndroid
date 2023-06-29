package com.example.taskappandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskappandroid.database.Task
import com.example.taskappandroid.repo.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository : TaskRepo
) : ViewModel(){

    val getAllTasks = repository.getAllTasks()
    val getAllPriorityTasks = repository.getAllPriorityTasks()

    fun insert(taskEntry: Task) = viewModelScope.launch {
        repository.insert(taskEntry)
    }

    fun delete(taskEntry: Task) = viewModelScope.launch{
        repository.deleteItem(taskEntry)
    }


    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return repository.searchDatabase(searchQuery)
    }

}