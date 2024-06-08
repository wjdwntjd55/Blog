package com.project.skeletonui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.skeletonui.databinding.ItemRvBinding

class MainAdapter(private val dataList: List<Plant>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant) {
            binding.textViewItemRv.text = item.name
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.imageViewItemRv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}