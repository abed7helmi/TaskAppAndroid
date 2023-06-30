package com.example.taskappandroid.utilities

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DateFormat

@SuppressLint("SetTextI18n")
@BindingAdapter("setPriority")
fun setPriority(view: TextView, priority: Int){
    when(priority){
        0 -> {
            view.text = "Haute priorité"
            view.setTextColor(Color.RED)
        }
        1 -> {
            view.text = "Priorité moyenne"
            view.setTextColor(Color.BLUE)
        }
        else -> {
            view.text = "Priorité basse"
            view.setTextColor(Color.GREEN)
        }
    }
}

@BindingAdapter("setTimestamp")
fun setTimestamp(view: TextView, timestamp: Long){
    view.text = DateFormat.getInstance().format(timestamp)
}