package ru.stplab.repository.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["translation"],
    foreignKeys = [ForeignKey(
        entity = WordEntity::class,
        parentColumns = ["word"],
        childColumns = ["wordId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["wordId"])]
)
class MeaningsEntity(
    val translation: String,
    val imageUrl: String?,
    val wordId: String
)