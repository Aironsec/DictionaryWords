package ru.stplab.dictionarywords.view.main

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.viewmodal.InteractorContract

class MainInteractor(
    private val remoteRepository: RepositoryContract<List<DataModel>>,
    private val localRepository: RepositoryContract<List<DataModel>>
) : InteractorContract<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState =
        AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
}
