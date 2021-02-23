package ru.stplab.dictionarywords.model.datasource

import ru.stplab.dictionarywords.model.data.DataModel

class RoomDataBaseImplementation : DataSourceContract<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
