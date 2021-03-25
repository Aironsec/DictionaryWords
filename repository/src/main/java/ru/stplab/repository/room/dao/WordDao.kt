package ru.stplab.repository.room.dao

import androidx.room.*
import ru.stplab.repository.room.dao.relation.WordMeanings
import ru.stplab.repository.room.entity.WordEntity
import ru.stplab.repository.room.entity.MeaningsEntity

@Dao
abstract class WordDao {
    @Query("SELECT * FROM WordEntity WHERE favorites = :favorites")
    abstract suspend fun getAllHistoryWord(favorites: Boolean): List<WordMeanings>

    @Query("SELECT * FROM WordEntity WHERE word LIKE :word")
    abstract suspend fun getDWord(word: String): WordEntity

    @Transaction
    @Query("SELECT * FROM WordEntity WHERE word LIKE :word")
    abstract suspend fun getWordAndMeanings(word: String): WordMeanings

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertMeanings(meanings: List<MeaningsEntity>)

    @Transaction @Insert
    suspend fun insertWordMeanings(word: WordEntity, meanings: List<MeaningsEntity>){
        insert(word)
        insertMeanings(meanings)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(entities: List<WordEntity>)

    @Update
    abstract suspend fun update(entity: WordEntity)

    @Delete
    abstract suspend fun delete(entity: WordEntity)

}