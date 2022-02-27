package com.oseong.ifeelalive.ui.favoritepictures.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.databinding.ItemBodyFavoriteBinding

class FavoritePicturesAdapter :
    ListAdapter<AstroPicture, RecyclerView.ViewHolder>(FavoriteDiffCallback) {

    inner class FavoritePictureViewHolder(val binding: ItemBodyFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            with(binding.root.context) {
                val viewName = getString(R.string.shared_view).plus(layoutPosition)
                ViewCompat.setTransitionName(binding.cardContainer, viewName)
            }

            with(binding) {
                this.item = item
                executePendingBindings()

                cardContainer.setOnClickListener {
                    navigateToDetail(item, it)
                }
            }
        }

        private fun navigateToDetail(item: AstroPicture, view: View) {
            val cardView = view.findViewById<CardView>(R.id.card_container)

            val extras = FragmentNavigatorExtras(cardView to cardView.transitionName)

            view.findNavController().navigate(
                R.id.navigate_to_detail_from_pager,
                bundleOf(
                    "picture" to item,
                    "view" to cardView.transitionName
                ),
                null,
                extras
            )
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