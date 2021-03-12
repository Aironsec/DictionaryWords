package ru.stplab.repository.repository

import ru.stplab.model.data.DataModel
import ru.stplab.repository.datasource.DataSourceContract

class RepositoryImplementation(private val dataSource: DataSourceContract<List<DataModel>>) :
    RepositoryContract<List<DataModel>> {

    override suspend fun getData(word: String, favorites: Boolean): List<DataModel> {
        return dataSource.getData(word, favorites)
    }
}
