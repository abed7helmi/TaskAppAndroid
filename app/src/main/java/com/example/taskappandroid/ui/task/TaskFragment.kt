package com.example.taskappandroid.ui.task


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController


import com.example.taskappandroid.viewmodel.TaskViewModel
import com.exemple.taskappandroid.R
import com.exemple.taskappandroid.databinding.FragmentTaskBinding

import kotlinx.coroutines.launch




class TaskFragment : Fragment() {
    private val viewModel : TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = TaskAdapter()

        /*viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }*/

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getAllTasks.collect{ tasks ->
                    Log.v("test",tasks.toString())
                    adapter.submitList(tasks)
                }
            }
        }

        binding.apply {
            binding.recyclerView.adapter = adapter ;

            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
            }
        }
        return binding.root ;
    }

}