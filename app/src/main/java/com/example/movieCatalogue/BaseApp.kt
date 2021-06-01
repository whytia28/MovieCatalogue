package com.example.movieCatalogue

import android.app.Application
import com.example.movieCatalogue.utils.appModule
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appModule) }
    }
}