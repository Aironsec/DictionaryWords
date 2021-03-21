package ru.stplab.repository.datasource

import ru.stplab.model.data.AppState

interface DataSourceLocal<T>: DataSourceContract<T> {
    suspend fun saveToDB(appState: AppState)
}