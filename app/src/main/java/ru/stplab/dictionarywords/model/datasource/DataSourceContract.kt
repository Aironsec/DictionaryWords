package ru.stplab.dictionarywords.model.datasource

interface DataSourceContract<T> {

    suspend fun getData(word: String): T
}