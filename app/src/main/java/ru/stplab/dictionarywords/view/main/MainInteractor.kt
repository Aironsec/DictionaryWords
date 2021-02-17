package ru.stplab.dictionarywords.view.main

import io.reactivex.Observable
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.presenter.InteractorContract

class MainInteractor(
    private val remoteRepository: RepositoryContract<List<DataModel>>,
    private val localRepository: RepositoryContract<List<DataModel>>
) : InteractorContract<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}
