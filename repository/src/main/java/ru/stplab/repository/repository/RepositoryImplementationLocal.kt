package ru.stplab.repository.repository

import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.repository.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) = dataSource.saveToDB(appState)

    override suspend fun getData(word: String, favorites: Boolean): List<DataModel> =
        dataSource.getData(word, favorites)

}