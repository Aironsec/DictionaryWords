package ru.stplab.dictionarywords.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.stplab.dictionarywords.view.main.MainActivity
import ru.stplab.dictionarywords.view.main.MainInteractor
import ru.stplab.dictionarywords.view.main.MainViewModel
import ru.stplab.favoritesscreen.FavoritesActivity
import ru.stplab.favoritesscreen.FavoritesInteractor
import ru.stplab.favoritesscreen.FavoritesViewModel
import ru.stplab.model.data.DataModel
import ru.stplab.repository.datasource.RetrofitImplementation
import ru.stplab.repository.datasource.RoomDataBaseImplementation
import ru.stplab.repository.repository.RepositoryContract
import ru.stplab.repository.repository.RepositoryImplementation
import ru.stplab.repository.repository.RepositoryImplementationLocal
import ru.stplab.repository.repository.RepositoryLocal
import ru.stplab.repository.room.db.DataBase

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(application, mainScreen, favoritesScreen, apiModule, netModule))
}

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
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val favoritesScreen = module {
    scope(named<FavoritesActivity>()) {
        scoped { FavoritesInteractor(get(), get()) }
        viewModel { FavoritesViewModel(get()) }
    }
}

