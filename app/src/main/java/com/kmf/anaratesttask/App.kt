package com.kmf.anaratesttask

import android.app.Application
import com.kmf.anaratesttask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App: Application() {

    companion object {
        const val TAG = "App"
    }

    override fun onCreate() {
        super.onCreate()
        //  Init Koin
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}