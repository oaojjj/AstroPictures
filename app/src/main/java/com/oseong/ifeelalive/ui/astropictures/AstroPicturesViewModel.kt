package com.oseong.ifeelalive.ui.astropictures

import androidx.lifecycle.*
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.data.Resource
import com.oseong.ifeelalive.data.source.PicturesRepository
import com.oseong.ifeelalive.ui.astropictures.adapter.ViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AstroPicturesViewModel @Inject constructor(
    private val picturesRepository: PicturesRepository
) : ViewModel() {

    private val _items: MutableLiveData<Resource<List<AstroPicture>>> = MutableLiveData()

    val items: LiveData<List<AstroPictureItem>> = _items.switchMap { handleAstroPictureItem(it) }

    private val _loading = MutableLiveData(true)
    //val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData(false)
    //private val error: LiveData<Boolean> = _error

    val loading: LiveData<Boolean> = Transformations.map(_loading) {
        it.or(_error.value!!)
    }

    private fun handleAstroPictureItem(resource: Resource<List<AstroPicture>>): LiveData<List<AstroPictureItem>> {
        val result = MutableLiveData<List<AstroPictureItem>>()
        when (resource) {
            is Resource.Loading -> _loading.value = true
            is Resource.Success -> {
                val astroPictureItems = mutableListOf<AstroPictureItem>()
                resource.data.forEach{ picture ->
                    astroPictureItems.add(
                        if (picture.isToday()) AstroPictureItem(picture, ViewType.Header)
                        else AstroPictureItem(picture)
                    )
                }
                result.value = astroPictureItems.reversed()
                _loading.value = false
            }
            is Resource.Error -> _error.value = true
        }
        return result
    }

    fun getAstroPictures(startDate: Long, endDate: Long) = viewModelScope.launch {
        _items.value = Resource.Loading()
        val response = picturesRepository.getAstroPictures(startDate, endDate)
        if (response.isSuccessful) {
            response.body()?.let { result ->
                _items.value = Resource.Success(result)
            }
        } else {
            _items.value = Resource.Error(response.message())
        }
    }
}
