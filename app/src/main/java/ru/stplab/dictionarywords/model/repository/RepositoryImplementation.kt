package ru.stplab.dictionarywords.model.repository

import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.DataSourceContract

class RepositoryImplementation(private val dataSource: DataSourceContract<List<DataModel>>) :
    RepositoryContract<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
