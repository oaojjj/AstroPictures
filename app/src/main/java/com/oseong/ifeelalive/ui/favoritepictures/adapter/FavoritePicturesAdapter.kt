package com.oseong.ifeelalive.ui.favoritepictures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.databinding.ItemBodyFavoriteBinding

class FavoritePicturesAdapter :
    ListAdapter<AstroPicture, RecyclerView.ViewHolder>(FavoriteDiffCallback) {

    inner class FavoritePictureViewHolder(val binding: ItemBodyFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritePictureViewHolder(
            ItemBodyFavoriteBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as FavoritePictureViewHolder).bind(item)
    }

    object FavoriteDiffCallback : DiffUtil.ItemCallback<AstroPicture>() {
        override fun areItemsTheSame(
            oldItem: AstroPicture,
            newItem: AstroPicture
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: AstroPicture,
            newItem: AstroPicture
        ): Boolean {
            return oldItem == newItem
        }
    }
}