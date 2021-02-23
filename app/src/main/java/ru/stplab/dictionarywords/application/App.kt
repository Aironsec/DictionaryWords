package ru.stplab.dictionarywords.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.stplab.dictionarywords.di.koin.application
import ru.stplab.dictionarywords.di.koin.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}