package com.example.kotlinflashcards.dao

import androidx.room.*
import com.example.kotlinflashcards.entities.Flashcard

@Dao
interface FlashcardDao {

    @Query("SELECT * FROM flashcard ORDER BY id DESC")
    suspend fun getAllNotes() : List<Flashcard>

    @Query("SELECT * FROM flashcard WHERE id =:id")
    suspend fun getSpecificNote(id:Int) : Flashcard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note:Flashcard)

    @Delete
    suspend fun deleteNote(note:Flashcard)

    @Query("DELETE FROM flashcard WHERE id =:id")
    suspend fun deleteSpecificNote(id:Int)

    @Update
    suspend fun updateNote(note:Flashcard)
}