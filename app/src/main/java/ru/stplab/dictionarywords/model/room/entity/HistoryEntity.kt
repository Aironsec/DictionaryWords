package ru.stplab.dictionarywords.model.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["word"], unique = true)])
class HistoryEntity(
    @PrimaryKey
    val word: String
//    val description: String?,
//    val imageUrl: String?
)