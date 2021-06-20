package com.noemi.android.popup.di

import com.noemi.android.popup.app.App
import com.noemi.android.popup.ui.MainActivity
import com.noemi.android.popup.ui.PixabayFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(mainActivity: MainActivity)

    fun inject(pixabayFragment: PixabayFragment)
}