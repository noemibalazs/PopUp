package com.noemi.android.popup.di

import com.noemi.android.popup.api.remoteDataSource.PixabayRemoteData
import com.noemi.android.popup.viewModel.PixabayViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providePicsumViewModel(pixabayRemoteData: PixabayRemoteData) =
        PixabayViewModel.Factory(pixabayRemoteData)
}