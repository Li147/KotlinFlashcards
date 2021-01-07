package com.example.kotlinflashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList()

        var numberOfColumns = 2

        rv_recyclerView.layoutManager = GridLayoutManager(this, 2)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList)
    }

    private fun addToList(title: String, description: String) {
        titlesList.add(title)
        descList.add(description)

    }

    private fun postToList() {
        for (i in 1..25) {
            addToList("Title $i", "Description $i")
        }
    }

}