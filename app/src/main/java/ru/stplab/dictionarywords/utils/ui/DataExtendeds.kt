package ru.stplab.dictionarywords.utils.ui

import ru.stplab.dictionarywords.model.data.AppState
import ru.stplab.dictionarywords.model.data.DataModel
import ru.stplab.dictionarywords.model.room.entity.HistoryEntity

fun DataModel.convertMeaningsToString(): String {
    var meaningsSeparatedByComma = String()
    meanings ?: return meaningsSeparatedByComma
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val dataModel= ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(DataModel(entity.word, null))
        }
    }
    return dataModel
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text)
            }
        }
        else -> null
    }
}
