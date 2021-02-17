package ru.stplab.dictionarywords.presenter

import io.reactivex.Observable

interface InteractorContract<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}