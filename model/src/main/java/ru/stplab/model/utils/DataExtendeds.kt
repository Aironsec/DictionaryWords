package ru.stplab.model.utils

import ru.stplab.model.data.DataModel

fun DataModel.convertMeaningsToString(): String {
    var meaningsSeparatedByComma = String()
    meanings ?: return meaningsSeparatedByComma
    for ((index, meaning) in meanings!!.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings!!.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}