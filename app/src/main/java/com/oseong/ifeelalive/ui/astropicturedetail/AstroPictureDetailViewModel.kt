package com.oseong.ifeelalive.ui.astropicturedetail

import androidx.lifecycle.*
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.FavoritesPicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AstroPictureDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val favoritesRepository: FavoritesPicRepository
) : ViewModel() {
    private val picture = savedStateHandle.get<AstroPicture>(PICTURE_SAVED_STATE_KEY)

    val isFavorite: LiveData<Boolean> = favoritesRepository.isFavorite(picture!!.url).asLiveData()

    fun clickFavorite() = viewModelScope.launch(Dispatchers.IO) {
        picture?.let {
            when (isFavorite.value) {
                true -> {
                    Timber.d("true")
                    favoritesRepository.unFavorite(picture)
                }
                false -> {
                    Timber.d("false")
                    favoritesRepository.favorite(picture)
                }
            }
        }
    }

    companion object {
        private const val PICTURE_SAVED_STATE_KEY = "picture"
    }
}