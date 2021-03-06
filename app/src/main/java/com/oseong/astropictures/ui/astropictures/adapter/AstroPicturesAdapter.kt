package com.oseong.astropictures.ui.astropictures.adapter

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
import com.oseong.astropictures.R
import com.oseong.astropictures.data.AstroPicture
import com.oseong.astropictures.data.AstroPictureItem
import com.oseong.astropictures.databinding.ItemBodyPictureBinding
import com.oseong.astropictures.databinding.ItemHeaderPictureBinding
import timber.log.Timber

enum class ViewType {
    Header, Body, Footer
}

class AstroPicturesAdapter :
    ListAdapter<AstroPictureItem, RecyclerView.ViewHolder>(AstroPictureDiffCallback) {

    // today astronomy picture
    inner class HeaderViewHolder(val binding: ItemHeaderPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            with(binding.root.context) {
                val viewName = getString(R.string.shared_view).plus(item.date)
                ViewCompat.setTransitionName(binding.cardContainer, viewName)
            }

            binding.astroPicture = item
            binding.executePendingBindings()
        }
    }

    // astronomy pictures except today
    inner class BodyViewHolder(private val binding: ItemBodyPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            with(binding.root.context) {
                val viewName = getString(R.string.shared_view).plus(item.date)
                ViewCompat.setTransitionName(binding.cardContainer, viewName)
                /*
                val imageName = getString(R.string.shared_picture).plus(layoutPosition)
                ViewCompat.setTransitionName(binding.ivThumbs, imageName)
                */
            }
            binding.astroPicture = item
            binding.executePendingBindings()
        }
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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
                FooterViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_footer_picture, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position).item

        holder.itemView.setOnClickListener {
            Timber.d("click$position")
            item?.let {
                navigateToDetail(it, holder.itemView)
            }
        }

        when (getItemViewType(position)) {
            ViewType.Header.ordinal -> (holder as HeaderViewHolder).bind(item!!)
            ViewType.Body.ordinal -> (holder as BodyViewHolder).bind(item!!)
        }
    }

    private fun navigateToDetail(item: AstroPicture, view: View) {
        val cardView = view.findViewById<CardView>(R.id.card_container)
        // val imageView = view.findViewById<ImageView>(R.id.iv_thumbs)

        val extras = FragmentNavigatorExtras(
            cardView to cardView.transitionName
        )

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

    override fun getItemViewType(position: Int): Int = getItem(position).type.ordinal

    object AstroPictureDiffCallback : DiffUtil.ItemCallback<AstroPictureItem>() {

        override fun areItemsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item?.url == newItem.item?.url
        }

        override fun areContentsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item == newItem.item
        }
    }
}

