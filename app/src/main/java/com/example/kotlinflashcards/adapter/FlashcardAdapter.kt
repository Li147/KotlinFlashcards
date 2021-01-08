package com.example.kotlinflashcards.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinflashcards.R
import com.example.kotlinflashcards.entities.Flashcard
import kotlinx.android.synthetic.main.item_rv_card.view.*
import java.util.ArrayList

class FlashcardAdapter () : RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder>()
{
    var listener: OnItemClickListener? = null
    var longClickListener: OnItemLongClickListener? = null
    var arrList = ArrayList<Flashcard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        return FlashcardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesList: List<Flashcard>){
        arrList = arrNotesList as ArrayList<Flashcard>
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    fun setOnLongClickListener(listener1: OnItemLongClickListener){
        longClickListener = listener1
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {

        holder.itemView.tv_cardTitle.text = arrList[position].cardTitle
        holder.itemView.tv_cardDetails.text = arrList[position].cardText

        holder.itemView.cv_cardView.setOnClickListener {
            listener!!.onClicked(arrList[position].id!! , holder.itemView)
        }


        holder.itemView.cv_cardView.setOnLongClickListener {
            longClickListener!!.onLongClicked(arrList[position].id!!)
        }
    }



    class FlashcardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    interface OnItemClickListener {
        fun onClicked(cardId: Int, view: View)
    }

    interface OnItemLongClickListener {
        fun onLongClicked(cardId: Int) : Boolean
    }

}