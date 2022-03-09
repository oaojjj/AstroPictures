package com.oseong.astropictures.data

import com.oseong.astropictures.ui.astropictures.adapter.ViewType

data class AstroPictureItem(val item: AstroPicture?, val type: ViewType = ViewType.Body)