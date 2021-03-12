package ru.stplab.dictionarywords.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.stplab.dictionarywords.view.main.MainInteractor
import ru.stplab.dictionarywords.view.main.MainViewModel
import ru.stplab.model.data.DataModel
import ru.stplab.repository.datasource.RetrofitImplementation
import ru.stplab.repository.datasource.RoomDataBaseImplementation
import ru.stplab.repository.repository.RepositoryContract
import ru.stplab.repository.repository.RepositoryImplementation
import ru.stplab.repository.repository.RepositoryImplementationLocal
import ru.stplab.repository.repository.RepositoryLocal
import ru.stplab.repository.room.db.DataBase

val application = module {
    single<RepositoryContract<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation(get()))
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }

    single {
        Room.databaseBuilder(get(), DataBase::class.java, "dictionaryDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<DataBase>().wordDao() }
}

val apiModule = module {
    single {
        getKoin().get<Retrofit>().create(ru.stplab.repository.api.ApiService::class.java)
    }
}

val netModule = module {
    val baseUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
    }
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { ru.stplab.historyscreen.HistoryInteractor(get(), get()) }
    factory { ru.stplab.historyscreen.HistoryViewModel(get()) }
}

val favoritesScreen = module {
    factory { ru.stplab.favoritesscreen.FavoritesInteractor(get(), get()) }
    factory { ru.stplab.favoritesscreen.FavoritesViewModel(get()) }
}

