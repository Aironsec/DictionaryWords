package ru.stplab.dictionarywords.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.search_dialog_fragment.*
import ru.stplab.dictionarywords.R

class SearchDialogFragment(
    private val onClickListener: ((String) -> Unit)? = null

) : BottomSheetDialogFragment() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var searchLayoutText: TextInputLayout
    private lateinit var searchButton: TextView

    private val textWatcher = object : MyOnTextChangeListener {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            searchButton.isEnabled = searchEditText.text != null && searchEditText.text.toString().isNotEmpty()
        }
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onClickListener?.invoke(searchEditText.text.toString())
            dismiss()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = search_edit_text
        searchLayoutText = search_input_layout
        searchButton = search_button_textview

        searchButton.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    private fun addOnClearClickListener() {
        searchLayoutText.setEndIconOnClickListener {
            searchEditText.setText("")
            searchButton.isEnabled = false
        }
    }
}
