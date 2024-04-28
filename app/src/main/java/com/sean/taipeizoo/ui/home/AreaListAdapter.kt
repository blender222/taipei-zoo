package com.sean.taipeizoo.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sean.taipeizoo.R
import com.sean.taipeizoo.common.toHttps
import com.sean.taipeizoo.databinding.ItemAreaBinding
import com.sean.taipeizoo.model.Area

class AreaListAdapter(
    private val toAreaFragment: (Int, String) -> Unit
) : RecyclerView.Adapter<AreaListAdapter.ViewHolder>() {
    var areaList = listOf<Area>()

    class ViewHolder(private val binding: ItemAreaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(area: Area, onClick: (Int, String) -> Unit) {
            with(binding) {
                name.text = area.name
                memo.text = area.memo.ifEmpty {
                    root.context.getString(R.string.no_closed_info)
                }
                info.text = area.info
                Glide.with(image)
                    .load(area.imageUrl.toHttps())
                    .into(image)
                root.setOnClickListener { onClick(area.id, area.name) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(areaList[position], toAreaFragment)
    }

    override fun getItemCount() = areaList.size
}