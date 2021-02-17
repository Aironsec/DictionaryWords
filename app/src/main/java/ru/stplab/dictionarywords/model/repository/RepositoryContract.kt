package ru.stplab.dictionarywords.model.repository

import io.reactivex.Observable

interface RepositoryContract<T> {

    fun getData(word: String): Observable<T>
}