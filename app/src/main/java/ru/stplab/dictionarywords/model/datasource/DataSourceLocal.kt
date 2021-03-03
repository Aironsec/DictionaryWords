package ru.stplab.dictionarywords.model.datasource

import ru.stplab.dictionarywords.model.data.AppState

interface DataSourceLocal<T>: DataSourceContract<T> {
    suspend fun saveToDB(appState: AppState)
}