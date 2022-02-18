package com.oseong.ifeelalive.ui.astropictures.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.databinding.ItemBodyPictureBinding
import com.oseong.ifeelalive.databinding.ItemHeaderPictureBinding
import com.oseong.ifeelalive.ui.astropictures.AstroPicturesViewModel
import timber.log.Timber

enum class ViewType {
    Header, Body, Footer
}

class AstroPicturesAdapter(private val vm: AstroPicturesViewModel) :
    ListAdapter<AstroPictureItem, RecyclerView.ViewHolder>(AstroPictureDiffCallback) {

    // today astronomy picture
    inner class HeaderViewHolder(private val binding: ItemHeaderPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            val name = binding.root.context.getString(R.string.shared_picture).plus(layoutPosition)

            ViewCompat.setTransitionName(binding.ivThumbs, name)
            binding.astroPicture = item
            binding.executePendingBindings()
        }
    }

    // astronomy pictures except today
    inner class BodyViewHolder(private val binding: ItemBodyPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AstroPicture) {
            val name = binding.root.context.getString(R.string.shared_picture).plus(layoutPosition)

            ViewCompat.setTransitionName(binding.ivThumbs, name)
            binding.astroPicture = item
            binding.executePendingBindings()
        }
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    Timber.d("end recyclerView")
                    vm.loadMoreAstroPictures()
                }
            }
        })
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
        val imageView = view.findViewById<ImageView>(R.id.iv_thumbs)

        val extras =
            FragmentNavigatorExtras(imageView to imageView.transitionName)

        Timber.d(imageView.transitionName)

        view.findNavController().navigate(
            R.id.navigate_to_detail_from_pager,
            bundleOf("item" to item, "name" to imageView.transitionName),
            null,
            extras
        )
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    object AstroPictureDiffCallback : DiffUtil.ItemCallback<AstroPictureItem>() {

        override fun areItemsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item?.id == newItem.item?.id
        }

        override fun areContentsTheSame(
            oldItem: AstroPictureItem,
            newItem: AstroPictureItem
        ): Boolean {
            return oldItem.item == newItem.item
        }
    }
}

