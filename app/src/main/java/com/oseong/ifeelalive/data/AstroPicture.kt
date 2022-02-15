package com.oseong.ifeelalive.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "astro_pic_table")
data class AstroPicture(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val copyright: String,
    val date: String,
    val explanation: String,
    @SerializedName("hdurl")
    val hdUrl: String,
    val media_type: String,
    val service_version: String,
    val thumbnail_url: String,
    val title: String,
    val url: String
)