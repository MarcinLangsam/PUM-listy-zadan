package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.ZadaniaItemsBinding

class ZadaniaAdapter(private val DataList: MutableList<Exercise>) :
    RecyclerView.Adapter<ZadaniaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZadaniaViewHolder {
        return ZadaniaViewHolder(
            ZadaniaItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }
    override fun getItemCount(): Int = DataList.size

    override fun onBindViewHolder(holder: ZadaniaViewHolder, position: Int) {
        val currentItem = DataList[position]
        holder.bind(currentItem)
    }

}