package com.noemi.android.popup.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noemi.android.popup.api.model.*
import com.noemi.android.popup.api.remoteDataSource.PixabayRemoteData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PixabayViewModel @Inject constructor(
    private val pixabayRemoteData: PixabayRemoteData
) :
    PixabayBaseViewModel() {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val artWorkList = MutableLiveData<List<Artwork>>()
    val similarArtWorks = MutableLiveData<ArrayList<String>>()

    fun getArtWorks() {
        compositeDisposable.clear()

        val disposable = pixabayRemoteData.getRemoteArtworks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progressEvent.value = true
            }
            .doFinally {
                progressEvent.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<ArtworkBlock>() {
                override fun onSuccess(response: ArtworkBlock) {
                    artWorkList.value = response.hits
                    Log.d(TAG, "onSuccess(): response size: ${response.hits.size}}")
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e.message ?: ERROR_MESSAGE
                    Log.e(TAG, "onError() - ${e.printStackTrace()}")
                }
            })

        compositeDisposable.add(disposable)
    }

    fun getSimilarArtWorks(): Disposable {
        return pixabayRemoteData.getSimilarArtworks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progressEvent.value = true
            }
            .doFinally {
                progressEvent.value = false
            }
            .subscribeWith(object : DisposableSingleObserver<ArtworkBlock>() {
                override fun onSuccess(response: ArtworkBlock) {
                    similarArtWorks.value = similarImageUrls(response)
                    Log.d(TAG, "onSuccess() - response size: ${response.hits.size}")
                }

                override fun onError(e: Throwable) {
                    errorEvent.value = e.message ?: ERROR_MESSAGE
                    Log.e(TAG, "onError() - ${e.printStackTrace()}")
                }
            })
    }

    private fun similarImageUrls(list: ArtworkBlock): ArrayList<String> {
        val mutableUrls = ArrayList<String>()
        list.hits.forEach {
            mutableUrls.add(it.url)
        }
        return mutableUrls
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val pixabayRemoteData: PixabayRemoteData) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PixabayViewModel(pixabayRemoteData) as T
        }
    }

    companion object {
        const val TAG = "PixabayViewModel"
        private const val ERROR_MESSAGE = "An error has occurred!"
    }
}