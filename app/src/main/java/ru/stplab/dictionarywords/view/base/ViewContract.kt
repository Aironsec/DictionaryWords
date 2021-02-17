package ru.stplab.dictionarywords.view.base

import ru.stplab.dictionarywords.model.data.AppState

interface ViewContract {

    fun renderData(appState: AppState)
}