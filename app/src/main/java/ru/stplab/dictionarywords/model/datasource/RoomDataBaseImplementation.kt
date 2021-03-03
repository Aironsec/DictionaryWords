package ru.stplab.dictionarywords.model.datasource

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.room.dao.HistoryDao
import ru.stplab.dictionarywords.utils.ui.convertDataModelSuccessToEntity
import ru.stplab.dictionarywords.utils.ui.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> =
        mapHistoryEntityToSearchResult(historyDao.all())

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
