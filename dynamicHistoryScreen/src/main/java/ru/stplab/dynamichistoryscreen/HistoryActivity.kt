package ru.stplab.dynamichistoryscreen

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.core.BaseActivity
import ru.stplab.descriptionscreen.DescriptionActivity
import ru.stplab.dynamichistoryscreen.adapter.HistoryAdapter
import ru.stplab.dynamichistoryscreen.di.injectDependencies
import ru.stplab.model.utils.convertMeaningsToString

class HistoryActivity : BaseActivity<AppState>(){
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter {
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

    override val layoutRes = R.layout.activity_history

    override lateinit var viewModel: HistoryViewModel

    private fun initView() {
        history_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        history_activity_recyclerview.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "History"

        injectDependencies()
        val model: HistoryViewModel by currentScope.inject()
        viewModel = model

        initView()
        viewModel.viewState.observe(this) { renderData(it) }
    }

    override fun onResume() {
        viewModel.getData("", false, false)
        super.onResume()
    }

    override fun setDataToAdapter(data: List<DataModel>){
        adapter.setData(data)
    }
}