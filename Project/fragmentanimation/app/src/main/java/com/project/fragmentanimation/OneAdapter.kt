package com.project.fragmentanimation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.fragmentanimation.databinding.ItemOneBinding

class OneAdapter(private val dataList: List<MainData>) : RecyclerView.Adapter<OneAdapter.OneViewHolder>() {

    inner class OneViewHolder(private val binding: ItemOneBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainData) {
            binding.imageViewItemOne.setImageResource(item.imageResId)
            binding.textViewItemOne.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOneBinding.inflate(inflater, parent, false)
        return OneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: OneViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}