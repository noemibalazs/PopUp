package com.noemi.android.popup

import android.os.Build
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.noemi.android.popup.api.dataSource.PixabayAPI
import com.noemi.android.popup.api.model.ArtworkBlock
import com.noemi.android.popup.api.remoteDataSource.PixabayRepository
import com.noemi.android.popup.util.IMAGE_TYPE
import com.noemi.android.popup.util.KEY
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.NullPointerException

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class RepositoryModuleUnitTest {

    private val pixabayAPI:PixabayAPI = mock()
    private lateinit var pixabayRepository: PixabayRepository
    private lateinit var failure: Throwable

    @Before
    fun setUp(){
        pixabayRepository = PixabayRepository(pixabayAPI)
        failure = Throwable("An error has occurred.")
    }

    @Test(expected = NullPointerException::class)
    fun testGetRemoteArtworksComplete(){
        val result = mock<ArtworkBlock>()
        whenever(pixabayRepository.getRemoteArtworks()).thenReturn(Single.just(result))
        pixabayRepository.getRemoteArtworks().test().assertComplete()
        verify(pixabayAPI).getArtWorks(KEY, "popular", 2, IMAGE_TYPE, "horse")
    }

    @Test(expected = NullPointerException::class)
    fun testGetRemoteArtworksError(){
        val result = mock<ArtworkBlock>()
        whenever(pixabayRepository.getRemoteArtworks()).thenReturn(Single.error(failure))
        pixabayRepository.getRemoteArtworks().test().assertError(failure)
        verify(pixabayAPI).getArtWorks(KEY, "popular", 2, IMAGE_TYPE, "horse")
    }

    @Test(expected = NullPointerException::class)
    fun testGetSimilarArtworksComplete(){
        val result = mock<ArtworkBlock>()
        whenever(pixabayRepository.getSimilarArtworks()).thenReturn(Single.just(result))
        pixabayRepository.getSimilarArtworks().test().assertComplete()
        verify(pixabayAPI).getArtWorks(KEY, "popular",3, IMAGE_TYPE, "snake")
    }

    @Test(expected = NullPointerException::class)
    fun testGetSimilarArtworksError(){
        whenever(pixabayRepository.getSimilarArtworks()).thenReturn(Single.error(failure))
        pixabayRepository.getSimilarArtworks().test().assertError(failure)
        verify(pixabayAPI).getArtWorks(KEY, "popular",3, IMAGE_TYPE, "snake")
    }
}