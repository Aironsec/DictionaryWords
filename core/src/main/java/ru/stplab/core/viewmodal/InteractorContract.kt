package ru.stplab.core.viewmodal

interface InteractorContract<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean, favorites: Boolean): T
}