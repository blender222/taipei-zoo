package com.sean.taipeizoo.ui.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sean.taipeizoo.R
import com.sean.taipeizoo.common.toHttps
import com.sean.taipeizoo.common.trimNewLineStart
import com.sean.taipeizoo.databinding.ItemAnimalBinding
import com.sean.taipeizoo.databinding.ItemAreaDetailBinding
import com.sean.taipeizoo.model.Animal
import com.sean.taipeizoo.model.Area

class AreaDetailAdapter(
    private val uiData: UiData,
    private val openLink: (String) -> Unit,
    private val onClick: (Animal) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class ViewType {
        AreaDetail, AnimalItem
    }

    class AreaDetailViewHolder(private val binding: ItemAreaDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(area: Area, openLink: (String) -> Unit) {
            with(binding) {
                info.text = area.info
                memo.text = area.memo.ifEmpty {
                    root.context.getString(R.string.no_closed_info)
                }
                category.text = area.category
                link.setOnClickListener { openLink(area.url) }
                Glide.with(image)
                    .load(area.imageUrl.toHttps())
                    .into(image)
            }
        }
    }

    class AnimalItemViewHolder(private val binding: ItemAnimalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal, onClick: (Animal) -> Unit) {
            with(binding) {
                name.text = animal.nameCh
                intro.text = animal.behavior
                    .ifEmpty { animal.feature }
                    .trimNewLineStart()
                Glide.with(image)
                    .load(animal.imageUrl.toHttps())
                    .into(image)
                root.setOnClickListener { onClick(animal) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.AreaDetail.ordinal) {
            val binding = ItemAreaDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AreaDetailViewHolder(binding)
        } else {
            val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AnimalItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            ViewType.AreaDetail.ordinal -> {
                (holder as AreaDetailViewHolder).bind(uiData.area, openLink)
            }
            ViewType.AnimalItem.ordinal -> {
                (holder as AnimalItemViewHolder).bind(uiData.animalList[position - 1], onClick)
            }
        }
    }

    override fun getItemCount(): Int = uiData.animalList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ViewType.AreaDetail.ordinal
        } else {
            ViewType.AnimalItem.ordinal
        }
    }
}