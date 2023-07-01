package com.example.taskappandroid.repo

import androidx.lifecycle.LiveData
import com.example.taskappandroid.database.Task
import com.example.taskappandroid.database.TaskDao
import javax.inject.Inject

class TaskRepo ( val taskDao: TaskDao){

    suspend fun insert(taskEntry: Task) = taskDao.insert(taskEntry)



    suspend fun deleteItem(taskEntry: Task) = taskDao.delete(taskEntry)

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getAllTasks() = taskDao.getAllTasks()
    //fun getAllTasks() : LiveData<List<Task>> = taskDao.getAllTasks()
    //fun getAllPriorityTasks() = taskDao.getAllPriorityTasks()



}

/*class TaskRepo @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insert(taskEntry: Task) = taskDao.insert(taskEntry)

    suspend fun updateData(taskEntry: Task) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: Task) = taskDao.delete(taskEntry)

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getAllTasks() = taskDao.getAllTasks()

    fun getAllPriorityTasks() = taskDao.getAllPriorityTasks()

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return taskDao.searchDatabase(searchQuery)
    }

}*/