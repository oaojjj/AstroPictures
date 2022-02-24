package com.oseong.ifeelalive.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// GsonConverter 문제인지 모르겠는데, non-null type인데 null로 초기화되버리는 이슈가 있음..
@Entity(tableName = "astro_pic_table")
data class AstroPicture(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val copyright: String?,
    val date: String,
    val explanation: String,
    @SerializedName("hdurl")
    val hdUrl: String?,
    val media_type: String,
    val service_version: String,
    val thumbnail_url: String?,
    val title: String,
    val url: String?
) : Serializable {

    fun getCopyrightAndDate(): String {
        return "${copyrightOrUnKnown()} / $date"
    }

    fun getThumbnail(): String? {
        return thumbnail_url ?: url
    }

    fun getImage(): String? {
        return if (isImage())
            url ?: thumbnail_url ?: hdUrl
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