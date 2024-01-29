package com.example.lista2

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.OcenyItemBinding

class OcenyViewHolder(private val binding: OcenyItemBinding) :
    RecyclerView.ViewHolder(binding.root){



    @SuppressLint("SetTextI18n")
    fun bind(DataListGradeMean: SubjectGrade){


        binding.przedmiotOceny.text = DataListGradeMean.subject
        binding.srednia.text = "Średnia: "+DataListGradeMean.mean.toString()
        binding.liczbaList.text = "Liczba list: "+DataListGradeMean.listNumber.toString()

    }
}