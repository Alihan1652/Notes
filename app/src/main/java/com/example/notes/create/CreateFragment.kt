package com.example.notes.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.App
import com.example.notes.data.local.models.TaskModel
import com.example.notes.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLister()
        }
    private fun setupLister(){
        binding.btnSave.setOnClickListener {
           addData()
            findNavController().navigateUp()
        }
    }
    private fun addData(){
        val title = binding.etTitle.text.toString()
        val desc = binding.tvDesc.text.toString()

        App.db.dao().addTask(TaskModel(title = title, desc = desc))
    }
    }
