package com.oseong.ifeelalive.astropictures

import android.app.Application
import androidx.lifecycle.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.data.Resource
import com.oseong.ifeelalive.data.source.PicturesRepository
import com.oseong.ifeelalive.astropictures.adapter.ViewType
import com.oseong.ifeelalive.utils.minusTwoWeeks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class AstroPicturesViewModel @Inject constructor(
    private val picturesRepository: PicturesRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _items: MutableLiveData<Resource<List<AstroPicture>>> = MutableLiveData()

    val items: LiveData<List<AstroPictureItem>> = _items.switchMap { handleAstroPictureItems(it) }

    private val _loading = MutableLiveData(true)

    private val _error = MutableLiveData(false)

    private val _firstLoad = MutableLiveData(true)
    val firstLoading = _firstLoad

    // 날짜 선택 기능 추가 생각해보기.
    private lateinit var pagingDate: LocalDate

    init {
        AndroidThreeTen.init(application)

        pagingDate = LocalDate.now()
        loadAstroPictures(pagingDate.minusTwoWeeks(), pagingDate)
    }

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
            astroPictureItems.removeLast()
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


    /**
     * @param sd startDate
     * @param ed endDate
     * ex) sd ~ ed -> 2020-02-02 ~ 2020-02-09
     */
    fun loadAstroPictures(sd: LocalDate, ed: LocalDate) = viewModelScope.launch {
        _items.value = Resource.Loading()
        val response = picturesRepository.getAstroPictures(sd, ed)

        if (response.isSuccessful) {
            response.body()?.let { result ->
                _items.value = Resource.Success(result.reversed())
            }
        } else {
            _items.value = Resource.Error(response.message())
        }

        pagingDate = sd.minusDays(1)
    }

    fun loadMoreAstroPictures() {
        loadAstroPictures(pagingDate.minusTwoWeeks(), pagingDate)
    }
}
