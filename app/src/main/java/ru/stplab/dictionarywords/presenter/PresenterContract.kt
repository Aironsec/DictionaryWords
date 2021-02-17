package ru.stplab.dictionarywords.presenter

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.view.base.ViewContract

interface PresenterContract<T: AppState, V: ViewContract> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}