package com.sean.taipeizoo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sean.taipeizoo.databinding.ItemAreaBinding
import com.sean.taipeizoo.model.Area

class AreaAdapter : RecyclerView.Adapter<AreaAdapter.ViewHolder>() {
    var areaList = listOf<Area>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(private val binding: ItemAreaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(area: Area) {
            with(binding) {
                name.text = area.name
                memo.text = area.memo.ifEmpty { "無休館資訊" }
                info.text = area.info
                Glide.with(image)
                    .load(area.imageUrl.replaceFirst("http", "https"))
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(areaList[position])
    }

    override fun getItemCount() = areaList.size
}