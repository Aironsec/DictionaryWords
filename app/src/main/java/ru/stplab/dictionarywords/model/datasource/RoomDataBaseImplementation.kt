package ru.stplab.dictionarywords.model.datasource

import io.reactivex.Observable
import ru.stplab.dictionarywords.model.data.DataModel

class RoomDataBaseImplementation : DataSourceContract<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
