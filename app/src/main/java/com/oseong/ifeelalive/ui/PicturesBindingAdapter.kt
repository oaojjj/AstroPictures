package com.oseong.ifeelalive.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.ui.astropictures.adapter.AstroPicturesAdapter
import timber.log.Timber

@BindingAdapter("app:imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("app:endScrollListener")
fun bindEndScrollListener(listView: RecyclerView, loadMore: () -> Unit) {
    listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Timber.d("$dx,$dy")
            if (!recyclerView.canScrollVertically(1)) {
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
    Timber.d("$boolean")
    when (boolean) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}