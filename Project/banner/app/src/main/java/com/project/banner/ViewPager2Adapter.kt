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

    // 무한 스크롤을 위해 매우 큰 카운트 값을 사용
    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: ViewPager2ViewHolder, position: Int) {
        // 실제 데이터 위치를 계산
        val realPosition = position % dataList.size
        holder.bind(dataList[realPosition])
    }

}