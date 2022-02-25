package com.oseong.ifeelalive.ui.astropictures

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.AstroPictureItem
import com.oseong.ifeelalive.data.Resource
import com.oseong.ifeelalive.data.source.PicturesRepository
import com.oseong.ifeelalive.ui.astropictures.adapter.ViewType
import com.oseong.ifeelalive.utils.minusTwoWeeks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AstroPicturesViewModel @Inject constructor(
    private val picturesRepository: PicturesRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _items: MutableLiveData<Resource<List<AstroPicture>>> = MutableLiveData()

    val items: LiveData<List<AstroPictureItem>> = _items.switchMap { handleAstroPictureItems(it) }

    private val _loading = MutableLiveData(true)

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _firstLoad = MutableLiveData(true)
    val firstLoading = _firstLoad

    // 날짜 선택 기능 추가 생각해보기.
    private var pagingDate: LocalDate

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
                return result
            }
            is Resource.Loading -> _loading.value = true
            is Resource.Error -> _message.value = "에러가 발생했습니다."
        }
        return result
    }

    private fun getAstroPictureItems(data: List<AstroPicture>): MutableList<AstroPictureItem> {
        val astroPictureItems = items.value?.toMutableList() ?: mutableListOf()

        // 리스트가 비어있지 않다면, footer(progressBar)를 삭제한다.
        if (!astroPictureItems.isNullOrEmpty()) {
            astroPictureItems.removeLast()
        }

        data.forEach { picture ->
            astroPictureItems.add(
                if (_firstLoad.value!!) {
                    _firstLoad.value = false
                    AstroPictureItem(picture, ViewType.Header)
                } else AstroPictureItem(picture, ViewType.Body)
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
    private fun loadAstroPictures(sd: LocalDate, ed: LocalDate) =
        viewModelScope.launch(Dispatchers.IO) {
            _items.postValue(Resource.Loading())
            try {
                val response = picturesRepository.getAstroPictures(sd, ed)
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        _items.postValue(Resource.Success(result.reversed()))
                        pagingDate = sd.minusDays(1)
                    }
                } else {
                    _items.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                if (e is UnknownHostException) {
                    _message.postValue("인터넷을 연결해 주세요.")
                }
            }
        }

    fun loadMoreAstroPictures() {
        loadAstroPictures(pagingDate.minusTwoWeeks(), pagingDate)
    }
}
