package com.project.practice_room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.practice_room.data.model.Plant
import com.project.practice_room.databinding.ItemMainBinding

class MainAdapter(private var dataList: List<Plant>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.apply {
                textViewItemName.text = item.name
                textViewItemDescription.text = item.description
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .into(imageViewItemMain)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun updateData(newDataList: List<Plant>) {
        this.dataList = newDataList
        notifyDataSetChanged()
    }
}