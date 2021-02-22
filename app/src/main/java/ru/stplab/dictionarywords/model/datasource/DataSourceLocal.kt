package ru.stplab.dictionarywords.model.datasource

import io.reactivex.Observable
import ru.stplab.dictionarywords.model.data.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSourceContract<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}
