package com.example.notes.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.local.models.TaskModel
import com.example.notes.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val listNotes = arrayListOf<TaskModel>()
    var onLongClick: ((TaskModel) -> Unit)? = null

    fun addNotes(list: List<TaskModel>) {
        listNotes.clear()
        listNotes.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(listNotes[position])

        // Долгое нажатие для удаления
        holder.itemView.setOnLongClickListener {
            onLongClick?.invoke(listNotes[position])
            true
        }
    }

    override fun getItemCount(): Int = listNotes.size

    class NotesViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) = with(binding) {
            tvTitle.text = taskModel.title
            tvDesc.text = taskModel.desc

            // форматирование времени
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            tvDate.text = sdf.format(Date(taskModel.createdAt))
        }
    }
}