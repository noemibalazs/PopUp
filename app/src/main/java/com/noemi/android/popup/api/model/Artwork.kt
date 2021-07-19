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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artwork

        if (id != other.id) return false
        if (tags != other.tags) return false
        if (user != other.user) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + tags.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }
}