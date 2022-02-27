package com.oseong.ifeelalive.ui.favoritepictures

import androidx.lifecycle.*
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.FavoritesPicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritePicturesViewModel @Inject constructor(
    favoritesRepository: FavoritesPicRepository
) : ViewModel() {
    val items: LiveData<List<AstroPicture>?> =
        favoritesRepository.getFavoritePictures().asLiveData()

    val isEmpty: LiveData<Boolean> = items.map {
        it.isNullOrEmpty()
    }
}