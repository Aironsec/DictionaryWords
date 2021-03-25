package ru.stplab.repository.room.dao.relation

import androidx.room.Embedded
import androidx.room.Relation
import ru.stplab.repository.room.entity.MeaningsEntity
import ru.stplab.repository.room.entity.WordEntity


data class WordMeanings (
    @Embedded
    val wordEntity: WordEntity,
    @Relation(entity = MeaningsEntity::class, parentColumn = "word", entityColumn = "wordId")
    val meanings: List<MeaningsEntity>
)