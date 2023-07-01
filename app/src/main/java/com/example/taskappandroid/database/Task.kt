package com.example.taskappandroid.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskappandroid.utilities.Constants.TASK_TABLE
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "task_table")
//@Entity(tableName = TASK_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Int,
    var timestamp: Long
):Parcelable