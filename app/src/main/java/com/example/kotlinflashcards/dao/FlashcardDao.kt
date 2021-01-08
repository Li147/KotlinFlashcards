package com.example.kotlinflashcards.dao

import androidx.room.*
import com.example.kotlinflashcards.entities.Flashcard

@Dao
interface FlashcardDao {

    @Query("SELECT * FROM flashcard ORDER BY id DESC")
    suspend fun getAllCards() : List<Flashcard>

    @Query("SELECT * FROM flashcard WHERE id =:id")
    suspend fun getSpecificCard(id:Int) : Flashcard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(note:Flashcard)

    @Delete
    suspend fun deleteCard(note:Flashcard)

    @Query("DELETE FROM flashcard WHERE id =:id")
    suspend fun deleteSpecificCard(id:Int)

    @Update
    suspend fun updateCard(note:Flashcard)
}