package ru.stplab.dictionarywords.di

import dagger.Module
import dagger.Provides
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: RepositoryContract<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: RepositoryContract<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
