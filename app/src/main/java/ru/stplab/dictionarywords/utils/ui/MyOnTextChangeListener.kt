package ru.stplab.dictionarywords.utils.ui

import android.text.Editable
import android.text.TextWatcher

interface MyOnTextChangeListener: TextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}