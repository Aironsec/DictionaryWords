package ru.stplab.dictionarywords.view.main

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.viewmodal.BaseViewModal

class MainViewModel (private val interactor: MainInteractor) : BaseViewModal<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    val viewState: LiveData<AppState>
        get() = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModalCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, online: Boolean) {
        withContext(Dispatchers.IO){
            val result = interactor.getData(word, online)
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