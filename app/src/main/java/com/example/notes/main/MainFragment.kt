package com.example.notes.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.App
import com.example.notes.R
import com.example.notes.data.local.models.TaskModel
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.main.adapter.NotesAdapter

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
        setupListener()
        setupAdapterListeners() // ⚡️ Добавь этот вызов
    }

    private fun initView() {
        binding.rvNotes.adapter = adapter
    }

    private fun getData() {
        val list: List<TaskModel> = App.db.dao().getAllTask()
        adapter.addNotes(list)
    }

    private fun setupListener() {
        binding.fbCreate.setOnClickListener {
            findNavController().navigate(R.id.createFragment)
        }
    }

    private fun setupAdapterListeners() {
        adapter.onLongClick = { task ->
            App.db.dao().deleteTask(task) // удаляем из базы
            getData() // обновляем список
        }
    }
}
