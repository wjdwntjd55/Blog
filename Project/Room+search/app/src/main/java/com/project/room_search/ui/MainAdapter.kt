package com.project.room_search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.room_search.data.local.entities.Search
import com.project.room_search.databinding.ItemSearchBinding

class MainAdapter(private val itemClickListener: OnItemClickListener) : ListAdapter<Search, MainAdapter.MainViewHolder>(diffUtil) {

    inner class MainViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.textViewSearchItem.text = item.contents
            binding.imageViewSearchItem.setOnClickListener {
                itemClickListener.removeItemClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickListener {
        fun removeItemClick(item: Search)
    }

}