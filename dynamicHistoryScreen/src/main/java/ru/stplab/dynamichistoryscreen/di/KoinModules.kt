package ru.stplab.dynamichistoryscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.stplab.dynamichistoryscreen.HistoryActivity
import ru.stplab.dynamichistoryscreen.HistoryInteractor
import ru.stplab.dynamichistoryscreen.HistoryViewModel


fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}