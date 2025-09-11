package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.notes.databinding.ItemOnBoardBinding

class OnBoardAdapter(
    private val onBoardList: List<OnBoardModel>,
    private val onStart: () -> Unit,
    private val onSkip: () -> Unit
) : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(onBoardList[position])
    }

    override fun getItemCount(): Int = onBoardList.size

    inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoardModel: OnBoardModel) {
            binding.img.load(onBoardModel.gifRes)

            binding.tvTitle.text = onBoardModel.title
            binding.tvDesc.text = onBoardModel.desc

            if (bindingAdapterPosition != onBoardList.size - 1) {
                binding.btnStart.visibility = View.GONE
                binding.tvSkip.visibility = View.VISIBLE
                binding.tvSkip.setOnClickListener { onSkip() }
                binding.btnStart.setOnClickListener(null)
            } else {
                binding.tvSkip.visibility = View.GONE
                binding.btnStart.visibility = View.VISIBLE
                binding.btnStart.setOnClickListener { onStart() }
            }
        }
    }
}