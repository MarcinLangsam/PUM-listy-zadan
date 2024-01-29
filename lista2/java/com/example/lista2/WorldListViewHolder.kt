package com.example.lista2

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding


class WordListViewHolder(private val binding: WordListItemBinding, private val manager: FragmentManager) :
    RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SetTextI18n")
    fun bind(DataList: ExerciseList){
        binding.przedmiot.text = DataList.subject.name
        binding.ocena.text = "Ocena: "+DataList.grade.toString()
        binding.numerListy.text = "Lisat: " + (position+1).toString()
        binding.liczbaZadan.text = "Liczba zadań:  " + DataList.exercises.size.toString()
        binding.rekord.setOnClickListener{
            val fragmentTransaction = manager.beginTransaction()
            fragmentTransaction.replace(com.example.lista2.R.id.frame_layout,Zadania(position,DataList.subject.name))
            fragmentTransaction.commit()
        }

    }




}
