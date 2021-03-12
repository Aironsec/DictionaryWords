package ru.stplab.repository.utils

import ru.stplab.model.data.AppState
import ru.stplab.model.data.DataModel
import ru.stplab.model.data.Meanings
import ru.stplab.model.data.Translation
import ru.stplab.repository.room.dao.relation.WordMeanings
import ru.stplab.repository.room.entity.MeaningsEntity
import ru.stplab.repository.room.entity.WordEntity

fun mapHistoryEntityToSearchResult(list: List<WordMeanings>): List<DataModel> =
    list.map { DataModel(it.wordEntity.word, convertWordMeaningsToMeanings(it.meanings)) }

fun convertWordMeaningsToMeanings(list: List<MeaningsEntity>): List<Meanings> =
    list.map { Meanings(Translation(it.translation), it.imageUrl) }



fun convertDataModelSuccessToWordEntity(appState: AppState): WordEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                WordEntity(searchResult[0].text)
            }
        }
        else -> null
    }
}

fun convertDataModelSuccessToMeaningsEntity(appState: AppState): List<MeaningsEntity>? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                searchResult[0].meanings?.mapNotNull {
                    it.translation?.text?.let { key ->
                        MeaningsEntity(
                            key,
                            it.imageUrl,
                            searchResult[0].text
                        )
                    }
                }
            }
        }
        else -> null
    }
}