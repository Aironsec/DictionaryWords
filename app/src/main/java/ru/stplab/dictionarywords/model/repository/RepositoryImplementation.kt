package ru.stplab.dictionarywords.model.repository

import io.reactivex.Observable
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.DataSourceContract

class RepositoryImplementation(private val dataSource: DataSourceContract<List<DataModel>>) :
    RepositoryContract<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
