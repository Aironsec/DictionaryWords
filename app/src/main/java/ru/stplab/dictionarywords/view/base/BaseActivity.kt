package ru.stplab.dictionarywords.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import geekbrains.ru.translator.utils.network.isOnline
import kotlinx.android.synthetic.main.loading_layout.*
import ru.stplab.dictionarywords.utils.ui.AlertDialogFragment
import ru.stplab.dictionarywords.R
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.viewmodal.BaseViewModal

abstract class BaseActivity<T: AppState>: AppCompatActivity(){

    abstract fun setDataToAdapter(data: List<DataModel>)

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

    fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    setDataToAdapter(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progress_bar_horizontal.visibility = View.VISIBLE
                    progress_bar_round.visibility = View.GONE
                    progress_bar_horizontal.progress = appState.progress
                } else {
                    progress_bar_horizontal.visibility = View.GONE
                    progress_bar_round.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appState.error.message)
            }
        }
    }

    private fun showViewWorking() {
        loading_frame_layout.visibility = View.GONE
    }

    private fun showViewLoading() {
        loading_frame_layout.visibility = View.VISIBLE
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}