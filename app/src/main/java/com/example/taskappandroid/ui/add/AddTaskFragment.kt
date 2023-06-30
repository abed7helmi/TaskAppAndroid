package com.example.taskappandroid.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.kotlinmvvmtodolist.R
import com.android.kotlinmvvmtodolist.databinding.FragmentAddTaskBinding
import com.example.taskappandroid.database.Task
import com.example.taskappandroid.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint


class AddTaskFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddTaskBinding.inflate(inflater)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)

        )

        binding.apply {
            spinner.adapter = myAdapter
            btnAdd.setOnClickListener {
                if (TextUtils.isEmpty((txtTask.text))) {
                    Toast.makeText(requireContext(), "vide !", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val titleTitle = txtTask.text.toString()
                val priority = spinner.selectedItemPosition

                val taskEntry = Task(
                    0,
                    titleTitle,
                    priority,
                    System.currentTimeMillis()
                )

                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(), "Ajout réussi!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addTaskFragment_to_taskFragment)
            }


            return binding.root

        }

    }
}