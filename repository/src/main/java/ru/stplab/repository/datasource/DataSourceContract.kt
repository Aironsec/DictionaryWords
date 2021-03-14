package ru.stplab.repository.datasource

interface DataSourceContract<T> {

    suspend fun getData(word: String, favorites: Boolean): T
}