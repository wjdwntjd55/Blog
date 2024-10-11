package com.project.paging_network.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.paging_network.data.mode.GithubResponseItem
import com.project.paging_network.databinding.ItemGithubBinding

class MainAdapter : PagingDataAdapter<GithubResponseItem, MainAdapter.MainViewHolder>(diffUtil) {

    inner class MainViewHolder(private val binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubResponseItem) {
            binding.textViewNameGithub.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<GithubResponseItem>() {
            override fun areItemsTheSame(oldItem: GithubResponseItem, newItem: GithubResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubResponseItem, newItem: GithubResponseItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}