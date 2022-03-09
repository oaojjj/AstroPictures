package com.oseong.astropictures.ui.astropicturedetail

import androidx.lifecycle.*
import com.oseong.astropictures.data.AstroPicture
import com.oseong.astropictures.data.source.FavoritesPicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                    favoritesRepository.unFavorite(picture)
                }
                false -> {
                    favoritesRepository.favorite(picture)
                }
            }
        }
    }

    companion object {
        private const val PICTURE_SAVED_STATE_KEY = "picture"
    }
}