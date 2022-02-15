package com.oseong.ifeelalive.ui.astropictures.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oseong.ifeelalive.data.AstroPicture

class AstroPicturesAdapter :
    ListAdapter<AstroPicture, RecyclerView.ViewHolder>(AstroPictureDiffCallback) {

    // today astronomy picture
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    // astronomy pictures except today
    inner class BodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    object AstroPictureDiffCallback : DiffUtil.ItemCallback<AstroPicture>() {

        override fun areItemsTheSame(oldItem: AstroPicture, newItem: AstroPicture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AstroPicture, newItem: AstroPicture): Boolean {
            return oldItem == newItem
        }
    }
}

