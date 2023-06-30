package com.example.taskappandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.taskappandroid.database.Task
import com.example.taskappandroid.database.TaskDatabase
import com.example.taskappandroid.repo.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*@HiltViewModel
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

}*/

class TaskViewModel(application: Application) : AndroidViewModel(application){
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepo
    val getAllTasks: LiveData<List<Task>>
    init {
        repository = TaskRepo(taskDao)
        getAllTasks = repository.getAllTasks()
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