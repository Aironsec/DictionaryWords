package ru.stplab.repository.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["word"], unique = true)])
class WordEntity(
    @PrimaryKey
    val word: String,
    val favorites: Boolean = false
)