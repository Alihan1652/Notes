package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarAddNote.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        val currentDate = SimpleDateFormat("dd MMM HH:mm", Locale.getDefault()).format(Date())
        binding.toolbarAddNote.title = currentDate

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}