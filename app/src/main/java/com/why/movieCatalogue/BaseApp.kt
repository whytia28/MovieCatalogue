package com.why.movieCatalogue

import android.app.Application
import com.why.movieCatalogue.utils.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(appModule) }
    }
}