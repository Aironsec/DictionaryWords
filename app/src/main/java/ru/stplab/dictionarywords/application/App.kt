package ru.stplab.dictionarywords.application

import android.app.Application
import ru.stplab.dictionarywords.di.AppComponent
import ru.stplab.dictionarywords.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}