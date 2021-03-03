package ru.stplab.dictionarywords.model.repository

import ru.stplab.dictionarywords.model.data.AppState

interface RepositoryLocal<T> : RepositoryContract<T> {
    suspend fun saveToDB(appState: AppState)
}