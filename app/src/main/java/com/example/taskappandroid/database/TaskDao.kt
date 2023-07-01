package com.example.taskappandroid.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(taskEntry: Task)
    @Delete
    suspend fun delete(taskEntry: Task)
    @Query("DELETE FROM task_table")
    suspend fun deleteAll()
    @Query("SELECT * FROM task_table ORDER BY timestamp DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY priority ASC")
    fun getAllPriorityTasks(): LiveData<List<Task>>



}
