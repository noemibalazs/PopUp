package com.noemi.android.popup.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Artwork(
    @Json(name = "id") val id: Int,
    @Json(name = "tags") val tags: String,
    @Json(name = "user") val user: String,
    @Json(name = "largeImageURL") val url: String
) : Parcelable {
    override fun toString(): String {
        return "Artwork: id=$id, tags='$tags', user='$user', url='$url'"
    }
}