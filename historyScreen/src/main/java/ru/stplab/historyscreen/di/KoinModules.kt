package ru.stplab.historyscreen.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.stplab.historyscreen.HistoryInteractor
import ru.stplab.historyscreen.HistoryViewModel

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}