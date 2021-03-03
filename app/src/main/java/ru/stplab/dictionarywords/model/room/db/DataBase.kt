package ru.stplab.dictionarywords.model.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.stplab.dictionarywords.model.room.dao.HistoryDao
import ru.stplab.dictionarywords.model.room.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}