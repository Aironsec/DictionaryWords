package ru.stplab.dictionarywords.model.repository

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) = dataSource.saveToDB(appState)

    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)

}