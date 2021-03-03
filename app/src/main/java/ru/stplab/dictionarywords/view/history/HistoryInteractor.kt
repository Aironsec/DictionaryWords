package ru.stplab.dictionarywords.view.history

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.model.repository.RepositoryLocal
import ru.stplab.dictionarywords.viewmodal.InteractorContract

class HistoryInteractor (
    private val repositoryRemote: RepositoryContract<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : InteractorContract<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
