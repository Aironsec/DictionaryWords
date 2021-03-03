package ru.stplab.dictionarywords.view.history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.stplab.dictionarywords.R
import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.utils.ui.convertMeaningsToString
import ru.stplab.dictionarywords.view.base.BaseActivity
import ru.stplab.dictionarywords.view.history.adapter.HistoryAdapter
import ru.stplab.dictionarywords.view.main.description.DescriptionActivity

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

    override val viewModel: HistoryViewModel by viewModel()

    private fun initView() {
        history_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        history_activity_recyclerview.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        initView()
        viewModel.viewState.observe(this) { renderData(it) }
    }

    override fun onResume() {
        viewModel.getData("", false)
        super.onResume()
    }

    override fun setDataToAdapter(data: List<DataModel>){
        adapter.setData(data)
    }
}