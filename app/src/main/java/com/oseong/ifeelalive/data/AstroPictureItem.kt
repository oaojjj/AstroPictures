package com.oseong.ifeelalive.data

import com.oseong.ifeelalive.astropictures.adapter.ViewType

data class AstroPictureItem(val item: AstroPicture?, val type: ViewType = ViewType.Body)