package ru.stplab.dictionarywords.model.datasource

import io.reactivex.Observable
import ru.stplab.dictionarywords.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSourceContract<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
