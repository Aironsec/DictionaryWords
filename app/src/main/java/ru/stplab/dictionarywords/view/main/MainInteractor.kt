package ru.stplab.dictionarywords.view.main

import io.reactivex.Observable
import ru.stplab.dictionarywords.di.NAME_LOCAL
import ru.stplab.dictionarywords.di.NAME_REMOTE
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.viewmodal.InteractorContract
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: RepositoryContract<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: RepositoryContract<List<DataModel>>
) : InteractorContract<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> =
        when (fromRemoteSource) {
            true -> {
                remoteRepository.getData(word).map {
                    AppState.Success(it)
                }
            }
            else -> {
                localRepository.getData(word).map {
                    AppState.Success(it)
                }
            }
        }
}
