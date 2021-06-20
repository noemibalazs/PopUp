package com.noemi.android.popup.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class PixabayBaseViewModel : ViewModel() {

    protected abstract val compositeDisposable: CompositeDisposable

    val progressEvent = MutableLiveData<Boolean>()
    val errorEvent = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}