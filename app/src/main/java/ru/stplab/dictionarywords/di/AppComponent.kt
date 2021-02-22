package ru.stplab.dictionarywords.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.stplab.dictionarywords.view.main.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}
