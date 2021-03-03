package ru.stplab.dictionarywords.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.ru.translator.utils.network.isOnline
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.dictionarywords.R
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.utils.ui.convertMeaningsToString
import ru.stplab.dictionarywords.view.base.BaseActivity
import ru.stplab.dictionarywords.view.history.HistoryActivity
import ru.stplab.dictionarywords.view.main.adapter.MainAdapter
import ru.stplab.dictionarywords.view.main.description.DescriptionActivity

class MainActivity : BaseActivity<AppState>() {

    private val adapter: MainAdapter by lazy {
        MainAdapter {
            startActivity(
                DescriptionActivity.getIntent(
                    this,
                    it.text,
                    it.convertMeaningsToString(),
                    it.meanings?.get(0)?.imageUrl
                )
            )
        }
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
        viewModel.viewState.observe(this) { renderData(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun setDataToAdapter(data: List<DataModel>){
        adapter.setData(data)
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "111222"
    }
}