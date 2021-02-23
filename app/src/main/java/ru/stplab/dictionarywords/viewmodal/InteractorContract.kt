package ru.stplab.dictionarywords.viewmodal

interface InteractorContract<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}