package com.noemi.android.popup.api.remoteDataSource

import com.noemi.android.popup.api.model.ArtworkBlock
import io.reactivex.Single

interface PixabayRemoteData {

    fun getRemoteArtworks(): Single<ArtworkBlock>

    fun getSimilarArtworks(): Single<ArtworkBlock>
}