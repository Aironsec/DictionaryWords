package ru.stplab.dictionarywords.view.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.stplab.dictionarywords.presenter.PresenterContract
import ru.stplab.dictionarywords.model.data.AppState

abstract class BaseActivity<T: AppState>: AppCompatActivity(), ViewContract {

    protected lateinit var presenter: PresenterContract<T, ViewContract>
    protected abstract fun createPresenter(): PresenterContract<T, ViewContract>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

    protected fun toast(text: String?){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}