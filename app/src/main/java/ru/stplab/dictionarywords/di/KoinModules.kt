package ru.stplab.dictionarywords.di.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.stplab.dictionarywords.di.NAME_LOCAL
import ru.stplab.dictionarywords.di.NAME_REMOTE
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.RetrofitImplementation
import ru.stplab.dictionarywords.model.datasource.RoomDataBaseImplementation
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.model.repository.RepositoryImplementation
import ru.stplab.dictionarywords.view.main.MainInteractor
import ru.stplab.dictionarywords.view.main.MainViewModel

val application = module {
    single<RepositoryContract<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryContract<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
