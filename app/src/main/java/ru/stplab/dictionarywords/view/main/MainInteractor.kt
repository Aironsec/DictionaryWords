package ru.stplab.dictionarywords.view.main

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.model.repository.RepositoryLocal
import ru.stplab.dictionarywords.viewmodal.InteractorContract

class MainInteractor(
    private val remoteRepository: RepositoryContract<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : InteractorContract<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }

}
