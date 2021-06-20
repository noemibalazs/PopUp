package com.noemi.android.popup.di

import com.noemi.android.popup.api.dataSource.PixabayAPI
import com.noemi.android.popup.api.remoteDataSource.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(pixabayAPI: PixabayAPI): PixabayRemoteData {
        return PixabayRepository(pixabayAPI)
    }
}