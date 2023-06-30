package com.example.taskappandroid.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinmvvmtodolist.databinding.LayoutRowBinding
import com.example.taskappandroid.database.Task

class TaskAdapter(val clickListener: TaskClickListener): ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback) {

    companion object TaskDiffCallback : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }

    class ViewHolder(private val binding: LayoutRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, clickListener: TaskClickListener){
            binding.task = task
            binding.clickListner = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        if(current != null){
            holder.bind(current, clickListener)
        }
    }
}

class TaskClickListener(val clickListener: (taskEntry: Task) -> Unit){
    fun onClick(taskEntry: Task) = clickListener(taskEntry)
}