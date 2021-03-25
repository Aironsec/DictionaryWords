package ru.stplab.repository.datasource

import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.repository.room.dao.WordDao
import ru.stplab.repository.utils.convertDataModelSuccessToMeaningsEntity
import ru.stplab.repository.utils.convertDataModelSuccessToWordEntity
import ru.stplab.repository.utils.mapHistoryEntityToSearchResult


class RoomDataBaseImplementation(private val wordDao: WordDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String, favorites: Boolean): List<DataModel> =
        mapHistoryEntityToSearchResult(wordDao.getAllHistoryWord(favorites))

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToWordEntity(appState)?.let { word ->
            convertDataModelSuccessToMeaningsEntity(appState)?.let { meanings ->
                wordDao.insertWordMeanings(word, meanings)
            } ?: wordDao.insert(word)
        }
    }
}
