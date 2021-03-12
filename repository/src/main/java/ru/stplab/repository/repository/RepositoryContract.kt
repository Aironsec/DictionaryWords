package ru.stplab.repository.repository

interface RepositoryContract<T> {

    suspend fun getData(word: String, favorites: Boolean): T
}