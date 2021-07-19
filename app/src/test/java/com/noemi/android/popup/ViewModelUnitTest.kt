package com.noemi.android.popup

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.noemi.android.popup.api.model.Artwork
import com.noemi.android.popup.api.model.ArtworkBlock
import com.noemi.android.popup.api.remoteDataSource.PixabayRemoteData
import com.noemi.android.popup.viewModel.PixabayViewModel
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.lang.NullPointerException

class ViewModelUnitTest {

    private val pixabayRemoteData: PixabayRemoteData = mock()
    private lateinit var failure: Throwable
    private lateinit var pixabayViewModel: PixabayViewModel
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<String> = mock()
    private val artworkListObserver: Observer<List<Artwork>> = mock()
    private val similarArtworkObserver: Observer<ArrayList<String>> = mock()

    @Before
    fun setUp() {
        pixabayViewModel = PixabayViewModel(pixabayRemoteData)
        failure = Throwable("An error has occurred.")
    }

    @Test(expected = NullPointerException::class)
    fun testGetArtworksComplete() {
        val result = mock<ArtworkBlock>()
        whenever(pixabayRemoteData.getRemoteArtworks()).thenReturn(Single.just(result))
        pixabayViewModel.getArtWorks()
        pixabayViewModel.artWorkList.observeForever(artworkListObserver)

        verify(progressObserver).onChanged(true)
        verify(progressObserver).onChanged(true)
        verify(artworkListObserver).onChanged(result.hits)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetArtworksError() {
        whenever(pixabayRemoteData.getRemoteArtworks()).thenReturn(Single.error(failure))
        pixabayViewModel.getArtWorks()
        pixabayViewModel.artWorkList.observeForever(artworkListObserver)

        val capture = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(artworkListObserver).onChanged(null)
        verify(errorObserver).onChanged(capture.capture())
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetSimilarArtworksComplete() {
        val result = mock<ArtworkBlock>()
        whenever(pixabayRemoteData.getSimilarArtworks()).thenReturn(Single.just(result))
        pixabayViewModel.getSimilarArtWorks()
        pixabayViewModel.similarArtWorks.observeForever(similarArtworkObserver)

        verify(progressObserver).onChanged(true)
        verify(similarArtworkObserver).onChanged(similarImageUrls(result))
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetSimilarArtworksError() {

        whenever(pixabayRemoteData.getSimilarArtworks()).thenReturn(Single.error(failure))
        pixabayViewModel.getSimilarArtWorks()
        pixabayViewModel.similarArtWorks.observeForever(similarArtworkObserver)

        val capture = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(similarArtworkObserver).onChanged(null)
        verify(errorObserver).onChanged(capture.capture())
        verify(progressObserver).onChanged(false)
    }

    private fun similarImageUrls(list: ArtworkBlock): ArrayList<String> {
        val mutableUrls = ArrayList<String>()
        list.hits.forEach {
            mutableUrls.add(it.url)
        }
        return mutableUrls
    }
}