package com.project.paging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.paging.data.Article
import com.project.paging.databinding.ArticleViewholderBinding

class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(diffUtil) {

    inner class ArticleViewHolder(private val binding: ArticleViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ArticleViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}