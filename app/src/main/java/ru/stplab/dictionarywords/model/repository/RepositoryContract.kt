package ru.stplab.dictionarywords.model.repository

interface RepositoryContract<T> {

    suspend fun getData(word: String): T
}