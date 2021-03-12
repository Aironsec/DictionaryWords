package ru.stplab.favoritesscreen

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stplab.model.data.AppState
import ru.stplab.core.viewmodal.BaseViewModal

class FavoritesViewModel (private val interactor: FavoritesInteractor) : BaseViewModal<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    val viewState: LiveData<AppState>
        get() = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean, favorites: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModalCoroutineScope.launch { startInteractor(word, isOnline, favorites) }
    }

    private suspend fun startInteractor(word: String, online: Boolean, favorites: Boolean) {
        withContext(Dispatchers.IO){
            val result = interactor.getData(word, online, favorites)
            _mutableLiveData.postValue(result)
        }
    }

    override fun handlerError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        super.onCleared()
        _mutableLiveData.value = AppState.Success(null)
    }
}