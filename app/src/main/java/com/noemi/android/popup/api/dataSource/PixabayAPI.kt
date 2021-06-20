package com.noemi.android.popup.api.dataSource

import com.noemi.android.popup.api.model.ArtworkBlock
import io.reactivex.Single
import retrofit2.http.*

interface PixabayAPI {

    @GET("?")
    fun getArtWorks(
        @Query("key") key: String,
        @Query("order") order: String,
        @Query("per_page") number: Int,
        @Query("image_type") photo: String,
        @Query("q") query: String
    ): Single<ArtworkBlock>
}