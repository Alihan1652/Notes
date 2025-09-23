package com.example.notes.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notes.data.local.models.TaskModel
import com.example.notes.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(val onLongClick:(TaskModel)-> Unit, val onClick:(TaskModel) -> Unit) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val listNotes = arrayListOf<TaskModel>()

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
    }

    override fun getItemCount(): Int = listNotes.size

    inner class NotesViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) = with(binding) {
            tvTitle.text = taskModel.title
            tvDesc.text = taskModel.desc
            itemView.setOnLongClickListener {
                onLongClick(taskModel)
                false
            }
            // время
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            tvDate.text = sdf.format(Date(taskModel.createdAt))

            // карточки цвет
            cardView.setCardBackgroundColor(
                android.graphics.Color.parseColor(taskModel.color)
            )

            // фото
            if (taskModel.imageUri != null) {
                ivPhoto.visibility = View.VISIBLE
                Glide.with(ivPhoto.context)
                    .load(taskModel.imageUri)
                    .into(ivPhoto)
            } else {
                ivPhoto.visibility = View.GONE
            }

            // клик события
            itemView.setOnLongClickListener {
                onLongClick(taskModel)
                false
            }
            itemView.setOnClickListener {
                onClick(taskModel)
            }
        }
    }
}