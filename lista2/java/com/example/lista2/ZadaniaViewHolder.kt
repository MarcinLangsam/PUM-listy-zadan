package com.example.lista2

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.ZadaniaItemsBinding

class ZadaniaViewHolder(private val binding: ZadaniaItemsBinding) :
    RecyclerView.ViewHolder(binding.root){


    @SuppressLint("SetTextI18n")
    fun bind(DataList: Exercise){
        binding.punkty.text = "pkt: "+DataList.points.toString()
        binding.numerZadania.text = "Zadanie "+(position+1).toString()
        binding.tresc.text = DataList.content
    }


    }