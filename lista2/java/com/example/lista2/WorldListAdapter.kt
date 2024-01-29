package com.example.lista2

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding

class WordListAdapter(
    private val DataList: MutableList<ExerciseList>,
    private val parentFragmentManager: FragmentManager
) :
    RecyclerView.Adapter<WordListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        return WordListViewHolder(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false

            ),parentFragmentManager)
    }
    override fun getItemCount(): Int = DataList.size


    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currentItem = DataList[position]
        holder.bind(currentItem)

    }



}