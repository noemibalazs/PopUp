package com.noemi.android.popup.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtworkBlock(
    @Json(name = "hits") val hits: MutableList<Artwork>
) {
    override fun toString(): String {
        return "ArtworkBlock: hits=$hits"
    }
}