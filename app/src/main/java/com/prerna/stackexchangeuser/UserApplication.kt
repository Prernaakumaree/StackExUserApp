package com.prerna.stackexchangeuser

import android.app.Application
import com.facebook.stetho.Stetho
import com.prerna.stackexchangeuser.di.dataModule
import com.prerna.stackexchangeuser.di.networkModule
import com.prerna.stackexchangeuser.di.presenterModule
import com.prerna.stackexchangeuser.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@UserApplication)
            modules(networkModule, dataModule, useCaseModule, presenterModule)
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}