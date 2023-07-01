package com.example.taskappandroid.ui.task


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


import com.example.taskappandroid.viewmodel.TaskViewModel
import com.exemple.taskappandroid.R
import com.exemple.taskappandroid.databinding.FragmentTaskBinding
import com.google.android.material.snackbar.Snackbar

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

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = adapter.currentList[position]
                viewModel.delete(task)

                Snackbar.make(binding.root,"Supprimé !", Snackbar.LENGTH_LONG).apply {
                    setAction("Annuler"){
                        viewModel.insert(task)
                    }
                    show()
                }
            }

        }).attachToRecyclerView(binding.recyclerView)

        setHasOptionsMenu(true)
        return binding.root ;
    }



    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){


            R.id.delete_all -> deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem() {
        AlertDialog.Builder(requireContext())
            .setTitle("Tous  supprimer")
            .setMessage("etes-vous sur?")
            .setPositiveButton("Oui"){dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("Non"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }



}


