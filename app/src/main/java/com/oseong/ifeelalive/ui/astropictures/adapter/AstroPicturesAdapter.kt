package com.oseong.ifeelalive.ui.astropictures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.databinding.ItemBodyPictureBinding
import com.oseong.ifeelalive.databinding.ItemHeaderPictureBinding

enum class ViewType {
    Header, Body
}

class AstroPicturesAdapter :
    ListAdapter<AstroPictureItem, RecyclerView.ViewHolder>(AstroPictureDiffCallback) {

    // today astronomy picture
    inner class HeaderViewHolder(private val binding: ItemHeaderPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            binding.astroPicture = item
        }
    }

    // astronomy pictures except today
    inner class BodyViewHolder(private val binding: ItemBodyPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            binding.astroPicture = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ViewType.Header.ordinal -> {
                HeaderViewHolder(ItemHeaderPictureBinding.inflate(layoutInflater, parent, false))
            }
            ViewType.Body.ordinal -> {
                BodyViewHolder(ItemBodyPictureBinding.inflate(layoutInflater, parent, false))
            }
            else -> {
                BodyViewHolder(ItemBodyPictureBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position).item
        when (getItemViewType(position)) {
            ViewType.Header.ordinal -> (holder as HeaderViewHolder).bind(item)
            ViewType.Body.ordinal -> (holder as BodyViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    object AstroPictureDiffCallback : DiffUtil.ItemCallback<AstroPictureItem>() {

        override fun areItemsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item.id == newItem.item.id
        }

        override fun areContentsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item == newItem.item
        }
    }
}

