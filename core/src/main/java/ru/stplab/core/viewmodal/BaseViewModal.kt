package ru.stplab.core.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.stplab.model.data.AppState

abstract class BaseViewModal<T : AppState>(
    protected val _mutableLiveData: MutableLiveData<T> = MutableLiveData()

) : ViewModel() {

    protected val viewModalCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handlerError(throwable)
        }
    )

    abstract fun handlerError(error: Throwable)

    abstract fun getData(word: String, isOnline: Boolean, favorites: Boolean)

    protected fun cancelJob() {
        viewModalCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}