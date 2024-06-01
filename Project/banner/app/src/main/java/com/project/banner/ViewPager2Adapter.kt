package com.project.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.banner.databinding.ItemBannerBinding

class ViewPager2Adapter(private val dataList: ArrayList<DataPage>): RecyclerView.Adapter<ViewPager2Adapter.ViewPager2ViewHolder>() {

    inner class ViewPager2ViewHolder(private val binding: ItemBannerBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataPage) {
            binding.linearLayoutItemBanner.setBackgroundResource(data.color)
            binding.textViewTitleItemBanner.text = data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2ViewHolder {
        return ViewPager2ViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewPager2ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

}