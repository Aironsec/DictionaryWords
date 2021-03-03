package ru.stplab.dictionarywords.di

import androidx.room.Room
import org.koin.dsl.module
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.datasource.RetrofitImplementation
import ru.stplab.dictionarywords.model.datasource.RoomDataBaseImplementation
import ru.stplab.dictionarywords.model.room.db.DataBase
import ru.stplab.dictionarywords.model.repository.RepositoryContract
import ru.stplab.dictionarywords.model.repository.RepositoryImplementation
import ru.stplab.dictionarywords.model.repository.RepositoryImplementationLocal
import ru.stplab.dictionarywords.model.repository.RepositoryLocal
import ru.stplab.dictionarywords.view.history.HistoryInteractor
import ru.stplab.dictionarywords.view.history.HistoryViewModel
import ru.stplab.dictionarywords.view.main.MainInteractor
import ru.stplab.dictionarywords.view.main.MainViewModel

val application = module {
    single<RepositoryContract<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }

    single { Room.databaseBuilder(get(), DataBase::class.java, "dictionaryDB").build() }
    single { get<DataBase>().historyDao() }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}

