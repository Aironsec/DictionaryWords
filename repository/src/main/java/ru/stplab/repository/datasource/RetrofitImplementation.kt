package ru.stplab.repository.datasource

import ru.stplab.model.data.DataModel

class RetrofitImplementation(private val apiService: ru.stplab.repository.api.ApiService) : DataSourceContract<List<DataModel>> {

    override suspend fun getData(word: String, favorites: Boolean): List<DataModel> {
        return apiService.searchAsync(word).await()
    }
}
