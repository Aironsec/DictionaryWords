package ru.stplab.dictionarywords.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.dictionarywords.R
import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.utils.network.isOnline
import ru.stplab.core.BaseActivity
import ru.stplab.descriptionscreen.DescriptionActivity
import ru.stplab.model.utils.convertMeaningsToString
import ru.stplab.dictionarywords.view.main.adapter.MainAdapter
import ru.stplab.favoritesscreen.FavoritesActivity
import ru.stplab.historyscreen.HistoryActivity

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

    private fun showSearchFragment() = SearchDialogFragment {
        isNetworkAvailable = isOnline(applicationContext)
        if (isNetworkAvailable) {
            viewModel.getData(it, isNetworkAvailable, false)
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

        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

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
            R.id.menu_favorites -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
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