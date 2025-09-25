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
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter(::onLongTaskClick, :: onClick)

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
            val action = MainFragmentDirections.actionMainFragmentToCreateFragment(null)
            findNavController().navigate(action)
        }
        binding.btnFilterDate.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()

            val datePicker = android.app.DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth, 0, 0, 0)
                    val startOfDay = calendar.timeInMillis

                    calendar.set(year, month, dayOfMonth, 23, 59, 59)
                    val endOfDay = calendar.timeInMillis

                    val list = App.db.dao().getNotesByDate(startOfDay, endOfDay)
                    adapter.addNotes(list)
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }
        binding.btnResetFilter.setOnClickListener {
            getData()
        }
    }

    private fun onClick(task: TaskModel){
        val action = MainFragmentDirections.actionMainFragmentToCreateFragment(task)
        findNavController().navigate(action)
    }

    private fun onLongTaskClick(task: TaskModel){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удалить заметку?")
            .setMessage("Вы уверены, что хотите удалить эту заметку?")
            .setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Удалить") { dialog, _ ->
                App.db.dao().deleteTask(task)
                getData()
            }
            .show()
    }
}
