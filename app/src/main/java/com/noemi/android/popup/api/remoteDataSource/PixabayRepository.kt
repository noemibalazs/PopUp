package com.noemi.android.popup.api.remoteDataSource

import com.noemi.android.popup.api.dataSource.PixabayAPI
import com.noemi.android.popup.api.model.ArtworkBlock
import com.noemi.android.popup.util.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class PixabayRepository @Inject constructor(private val pixabayAPI: PixabayAPI) :
    PixabayRemoteData {

    override fun getRemoteArtworks(): Single<ArtworkBlock> {
        val order = getOrderType()
        val type = getRandomSearchType()
        return pixabayAPI.getArtWorks(KEY, order, 50, IMAGE_TYPE, type)
    }

    override fun getSimilarArtworks(): Single<ArtworkBlock> {
        val order = getOrderType()
        val type = getRandomSearchType()
        return pixabayAPI.getArtWorks(KEY, order, 3, IMAGE_TYPE, type)
    }

    private fun getOrderType(): String {
        val typeList = mutableListOf("latest", "popular")
        val type = Random.nextInt(typeList.size)
        return typeList[type]
    }

    private fun getRandomSearchType(): String {
        val searchableList = mutableListOf(
            "puma",
            "tiger",
            "jaguar",
            "gecko",
            "horse",
            "snake",
            "lion",
            "wolf"
        )
        val type = Random.nextInt(searchableList.size)
        return searchableList[type]
    }
}