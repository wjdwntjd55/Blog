package com.project.maintainfragmentstate.ui.one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.maintainfragmentstate.data.remote.model.Plant
import com.project.maintainfragmentstate.databinding.ItemOneBinding

class OneAdapter(val dataList: List<Plant>): RecyclerView.Adapter<OneAdapter.OneViewHolder>() {

    inner class OneViewHolder(val binding: ItemOneBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant) {
            binding.textViewItemOne.text = item.name
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.imageViewItemOne)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneViewHolder {
        return OneViewHolder(ItemOneBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: OneViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}