package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.OcenyItemBinding

class OcenyAdapter(private val DataListGradeMean: MutableList<SubjectGrade>) :
    RecyclerView.Adapter<OcenyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OcenyViewHolder {
        return OcenyViewHolder(
            OcenyItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }
    override fun getItemCount(): Int = DataListGradeMean.size

    override fun onBindViewHolder(holder: OcenyViewHolder, position: Int) {
        val currentItem = DataListGradeMean[position]
        holder.bind(currentItem)
    }

}