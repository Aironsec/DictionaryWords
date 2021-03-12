package ru.stplab.dictionarywords.view.main

import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.repository.repository.RepositoryContract
import ru.stplab.repository.repository.RepositoryLocal
import ru.stplab.core.viewmodal.InteractorContract

class MainInteractor(
    private val remoteRepository: RepositoryContract<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : InteractorContract<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean, favorites: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word, favorites))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word, favorites))
        }
        return appState
    }

}
