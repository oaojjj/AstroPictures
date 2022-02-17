package com.oseong.ifeelalive.ui.astropictures

import android.app.Application
import androidx.lifecycle.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.data.Resource
import com.oseong.ifeelalive.data.source.PicturesRepository
import com.oseong.ifeelalive.ui.astropictures.adapter.ViewType
import com.oseong.ifeelalive.utils.minusTwoWeeks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AstroPicturesViewModel @Inject constructor(
    private val picturesRepository: PicturesRepository,
    application: Application
) : AndroidViewModel(application) {

    init {
        AndroidThreeTen.init(application)
    }

    private val _items: MutableLiveData<Resource<List<AstroPicture>>> = MutableLiveData()

    val items: LiveData<List<AstroPictureItem>> = _items.switchMap { handleAstroPictureItems(it) }

    private val _loading = MutableLiveData(true)

    private val _error = MutableLiveData(false)

    private val _firstLoad = MutableLiveData(true)
    val firstLoading = _firstLoad

/*
    val loading: LiveData<Boolean> = Transformations.map(_loading) {
        it.or(_error.value!!)
    }
*/

    private fun handleAstroPictureItems(resource: Resource<List<AstroPicture>>): LiveData<List<AstroPictureItem>> {
        val result = MutableLiveData<List<AstroPictureItem>>()
        when (resource) {
            is Resource.Success -> {
                result.value = getAstroPictureItems(resource.data)
                _loading.value = false
                _error.value = false
                return result
            }
            is Resource.Loading -> _loading.value = true
            is Resource.Error -> _error.value = true
        }
        return result
    }

    private fun getAstroPictureItems(data: List<AstroPicture>): MutableList<AstroPictureItem> {
        val astroPictureItems = mutableListOf<AstroPictureItem>()

        if (!items.value.isNullOrEmpty()) {
            astroPictureItems.addAll(items.value!!)
        }

        data.forEach { picture ->
            astroPictureItems.add(
                if (_firstLoad.value!!) {
                    _firstLoad.value = false
                    AstroPictureItem(picture, ViewType.Header)
                } else AstroPictureItem(picture)
            )
        }

        astroPictureItems.add(AstroPictureItem(null, ViewType.Footer))
        return astroPictureItems
    }

    // 날짜 선택 기능 추가 생각해보기.
    private val today: LocalDate = LocalDate.now()

    private var pagingDate = today.minusTwoWeeks()

    fun loadAstroPictures(
        sd: LocalDate = pagingDate,
        ed: LocalDate = today
    ) = viewModelScope.launch {
        if (_firstLoad.value == true) {
            _items.value = Resource.Loading()
            val response = picturesRepository.getAstroPictures(sd, ed)

            if (response.isSuccessful) {
                response.body()?.let { result ->
                    _items.value = Resource.Success(result.reversed())
                }
            } else {
                _items.value = Resource.Error(response.message())
            }

            calPagingDate(sd)
        }
    }

    fun loadMoreAstroPictures() {
        val newEndDate = pagingDate
        val newStartDate = newEndDate.minusTwoWeeks()

        loadAstroPictures(newStartDate, newEndDate)
        calPagingDate(newStartDate)
    }

    private fun calPagingDate(sd: LocalDate) {
        pagingDate = sd.minusDays(1)
    }
}
