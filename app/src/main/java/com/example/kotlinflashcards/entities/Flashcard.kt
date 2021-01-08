package com.example.kotlinflashcards.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Flashcard: Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo(name = "card_title")
    var cardTitle:String? = null

    @ColumnInfo(name = "card_text")
    var cardText:String? = null
}