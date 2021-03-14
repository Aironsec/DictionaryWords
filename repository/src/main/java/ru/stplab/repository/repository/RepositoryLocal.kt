package ru.stplab.repository.repository

import ru.stplab.model.data.AppState

interface RepositoryLocal<T> : RepositoryContract<T> {
    suspend fun saveToDB(appState: AppState)
}