package com.example.taskappandroid.repo


import androidx.lifecycle.LiveData
import com.example.taskappandroid.database.Task
import com.example.taskappandroid.database.TaskDao


class TaskRepo ( val taskDao: TaskDao){

    suspend fun insert(taskEntry: Task) = taskDao.insert(taskEntry)

    suspend fun deleteItem(taskEntry: Task) = taskDao.delete(taskEntry)

    suspend fun deleteAll() {
        taskDao.deleteAll()
    }

    fun getAllTasks() = taskDao.getAllTasks()

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> {
        return taskDao.searchDatabase(searchQuery)
    }




}
