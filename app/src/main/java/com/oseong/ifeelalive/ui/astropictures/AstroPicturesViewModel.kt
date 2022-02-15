package com.oseong.ifeelalive.ui.astropictures

import androidx.lifecycle.ViewModel
import com.oseong.ifeelalive.data.source.PicturesRepository
import javax.inject.Inject

class AstroPicturesViewModel @Inject constructor(
    val picturesRepository: PicturesRepository
) : ViewModel() {

}