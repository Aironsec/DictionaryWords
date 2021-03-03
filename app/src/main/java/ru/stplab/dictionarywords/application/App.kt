package ru.stplab.dictionarywords.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.stplab.dictionarywords.di.application
import ru.stplab.dictionarywords.di.historyScreen
import ru.stplab.dictionarywords.di.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}