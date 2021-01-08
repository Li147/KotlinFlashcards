package com.example.kotlinflashcards.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinflashcards.dao.FlashcardDao
import com.example.kotlinflashcards.entities.Flashcard

@Database(entities = [Flashcard::class], version = 1, exportSchema = false)
abstract class FlashcardDatabase : RoomDatabase() {

    companion object {
        var flashcardDatabase: FlashcardDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): FlashcardDatabase {
            if (flashcardDatabase == null) {
                flashcardDatabase = Room.databaseBuilder(
                    context,
                    FlashcardDatabase::class.java,
                    "myflashcards.db"
                ).build()
            }
            // throws error if database is null !! double bang operator
            return flashcardDatabase!!
        }
    }

    abstract fun flashcardDao():FlashcardDao
}