package ru.stplab.dictionarywords.viewmodal

import io.reactivex.Observable

interface InteractorContract<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}