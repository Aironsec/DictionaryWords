package ru.stplab.dictionarywords.di

import dagger.Module
import dagger.Provides
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.DataSourceContract
import ru.stplab.dictionarywords.model.datasource.RetrofitImplementation
import ru.stplab.dictionarywords.model.datasource.RoomDataBaseImplementation
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSourceContract<List<DataModel>>): RepositoryContract<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSourceContract<List<DataModel>>): RepositoryContract<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSourceContract<List<DataModel>> = RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSourceContract<List<DataModel>> = RoomDataBaseImplementation()
}
