package com.oseong.astropictures.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oseong.astropictures.R
import com.oseong.astropictures.data.AstroPictureItem
import com.oseong.astropictures.ui.astropictures.adapter.AstroPicturesAdapter
import timber.log.Timber

@BindingAdapter("app:imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.place_holder)
            .into(view)
    }
}

@BindingAdapter("app:endScrollListener")
fun bindEndScrollListener(listView: RecyclerView, loadMore: () -> Unit) {
    listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!recyclerView.canScrollVertically(1) && recyclerView.canScrollVertically(-1)) {
                Timber.d("end recyclerView")
                loadMore.invoke()
            }
        }
    })
}

@BindingAdapter("app:items")
fun bindItems(listView: RecyclerView, items: List<AstroPictureItem>?) {
    Timber.d("$items")
    items?.let {
        (listView.adapter as AstroPicturesAdapter).submitList(items)
    }
}

@BindingAdapter("app:visible")
fun bindVisible(view: View, boolean: Boolean) {
    when (boolean) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}

@BindingAdapter("app:iconChange")
fun bindIconChange(view: FloatingActionButton, isFavorite: Boolean?) {
    Timber.d("isFavorite:${isFavorite}")
    if (isFavorite == null || !isFavorite)
        view.setImageResource(R.drawable.ic_favorite_24)
    else {
        view.setImageResource(R.drawable.ic_delete_24)
    }
}
