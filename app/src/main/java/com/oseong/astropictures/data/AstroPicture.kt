package com.oseong.astropictures.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// GsonConverter 문제인지 모르겠는데, non-null type인데 null로 초기화되버리는 이슈가 있음..
@Entity(tableName = "astro_pic_table")
data class AstroPicture(
    val copyright: String?,
    val date: String,
    val explanation: String,
    @SerializedName("hdurl") val hdUrl: String?,
    val media_type: String,
    val service_version: String,
    val thumbnail_url: String?,
    val title: String,
    @PrimaryKey val url: String
) : Serializable {

    fun getCopyrightAndDate(): String {
        return "${copyrightOrUnKnown()} / $date"
    }

    private fun getThumbnail(): String {
        return thumbnail_url ?: hdUrl ?: url
    }

    fun getImage(): String {
        return if (isImage())
            thumbnail_url ?: url
        else
            getThumbnail()
    }

    private fun isImage(): Boolean {
        return media_type == "image"
    }

    fun copyrightOrUnKnown(): String {
        return copyright ?: "UnKnown"
    }
}