package ru.stplab.repository.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.stplab.repository.room.dao.WordDao
import ru.stplab.repository.room.entity.MeaningsEntity
import ru.stplab.repository.room.entity.WordEntity

@Database(entities = [WordEntity::class, MeaningsEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}