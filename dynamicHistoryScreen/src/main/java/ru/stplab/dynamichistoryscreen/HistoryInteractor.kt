package ru.stplab.dynamichistoryscreen

import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.repository.repository.RepositoryContract
import ru.stplab.repository.repository.RepositoryLocal
import ru.stplab.core.viewmodal.InteractorContract

class HistoryInteractor (
    private val repositoryRemote: RepositoryContract<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : InteractorContract<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean, favorites: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word, favorites)
        )
    }
}
