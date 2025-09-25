package com.example.notes.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.App
import com.example.notes.data.local.models.TaskModel
import com.example.notes.databinding.FragmentCreateBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private val args: CreateFragmentArgs by navArgs()
    private var task: TaskModel? = null

    private var selectedColor: String = "#FFFFFF"
    private var selectedDate: Long = System.currentTimeMillis()
    private var selectedImageUri: String? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it.toString()
            binding.ivPhoto.visibility = View.VISIBLE
            binding.ivPhoto.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = args.model
        args.model?.let { task ->
            binding.etTitle.setText(task.title)
            binding.tvDesc.setText(task.desc)
            selectedColor = task.color ?: "#FFFFFF"
            selectedDate = task.createdAt
            selectedImageUri = task.imageUri
            selectedImageUri?.let { binding.ivPhoto.setImageURI(android.net.Uri.parse(it)) }
        }
        setupListeners()
        updateDatePreview()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            addData()
            findNavController().navigateUp()
        }

        binding.btnChooseColor.setOnClickListener {
            showColorDialog()
        }


        binding.btnAddPhoto.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun showColorDialog() {
        val colors = arrayOf( "black","red", "green", "blue",)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Поменять цвет")
            .setItems(colors) { _, which ->
                selectedColor = colors[which]
            }
            .show()
    }

    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate

        DatePickerDialog(requireContext(), { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            TimePickerDialog(requireContext(), { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                selectedDate = calendar.timeInMillis
                updateDatePreview()
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun updateDatePreview() {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        binding.tvDate.text = sdf.format(Date(selectedDate))
    }

    private fun addData() {
        val title = binding.etTitle.text.toString()
        val desc = binding.tvDesc.text.toString()

        val note = task
        if (note != null) {
            App.db.dao().updateNote(
                note.copy(
                    title = title,
                    desc = desc,
                    color = selectedColor,
                    createdAt = selectedDate,
                    imageUri = selectedImageUri
                )
            )
        } else {
            App.db.dao().addTask(
                TaskModel(
                    title = title,
                    desc = desc,
                    color = selectedColor,
                    createdAt = selectedDate,
                    imageUri = selectedImageUri
                )
            )
        }
    }
}