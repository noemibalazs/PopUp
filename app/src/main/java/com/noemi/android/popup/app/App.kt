package com.noemi.android.popup.app

import android.app.Application
import com.noemi.android.popup.di.AppComponent
import com.noemi.android.popup.di.AppModule
import com.noemi.android.popup.di.DaggerAppComponent

class App : Application() {

    lateinit var componemt: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        componemt = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        componemt.inject(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}