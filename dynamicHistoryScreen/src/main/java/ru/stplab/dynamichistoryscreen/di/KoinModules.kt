package ru.stplab.dynamichistoryscreen.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.stplab.dynamichistoryscreen.HistoryInteractor
import ru.stplab.dynamichistoryscreen.HistoryViewModel


fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}