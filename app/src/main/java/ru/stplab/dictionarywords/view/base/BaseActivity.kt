package ru.stplab.dictionarywords.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import geekbrains.ru.translator.utils.network.isOnline
import geekbrains.ru.translator.utils.ui.AlertDialogFragment
import ru.stplab.dictionarywords.R
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.viewmodal.BaseViewModal

abstract class BaseActivity<T: AppState>: AppCompatActivity(){

    abstract fun renderData(appState: AppState)

    abstract val viewModel: BaseViewModal<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() =
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )

    protected fun showAlertDialog(title: String?, message: String?) =
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)


    private fun isDialogNull(): Boolean = supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null


    protected fun toast(text: String?) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}