package ru.stplab.dictionarywords.model.datasource

import io.reactivex.Observable

interface DataSourceContract<T> {

    fun getData(word: String): Observable<T>
}