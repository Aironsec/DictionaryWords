package ru.stplab.favoritesscreen

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.core.BaseActivity
import ru.stplab.descriptionscreen.DescriptionActivity
import ru.stplab.favoritesscreen.adapter.FavoritesAdapter
import ru.stplab.model.utils.convertMeaningsToString


class FavoritesActivity : BaseActivity<AppState>(){
    private val adapter: FavoritesAdapter by lazy {
        FavoritesAdapter {
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

    override val viewModel: FavoritesViewModel by viewModel()

    private fun initView() {
        favorites_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        favorites_activity_recyclerview.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        supportActionBar?.title = "Favorites"

        initView()
        viewModel.viewState.observe(this) { renderData(it) }
    }

    override fun onResume() {
        viewModel.getData("", false, true)
        super.onResume()
    }

    override fun setDataToAdapter(data: List<DataModel>){
        adapter.setData(data)
    }
}