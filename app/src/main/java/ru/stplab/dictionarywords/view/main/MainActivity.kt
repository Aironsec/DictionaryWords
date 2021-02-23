package ru.stplab.dictionarywords.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.ru.translator.utils.network.isOnline
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.dictionarywords.R
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.view.base.BaseActivity
import ru.stplab.dictionarywords.view.main.adapter.MainAdapter

class MainActivity : BaseActivity<AppState>() {

    private val adapter: MainAdapter by lazy {
        MainAdapter { toast(it.text) }
    }

    override val viewModel: MainViewModel by viewModel()

// TODO: 22.02.2021 скажите на сколько твкой подход хорошь или плох,
//  передовать слики в конструкторе лямдой без использования интерфейсов

    private fun showSearchFragment() = SearchDialogFragment {
        isNetworkAvailable = isOnline(applicationContext)
        if (isNetworkAvailable) {
            viewModel.getData(it, isNetworkAvailable)
        } else {
            showNoInternetConnectionDialog()
        }
    }.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)

    private fun initView() {
        search_fab.setOnClickListener { showSearchFragment() }
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        viewModel.viewState.observe(this, Observer<AppState> { renderData(it) })
    }

    override fun renderData(appState: AppState) {
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
                    adapter.setData(data)
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
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "111222"
    }
}