package com.example.lista2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.FragmentZadaniaBinding

class Zadania(private val number: Int, private val subject: String) : Fragment() {

    private lateinit var binding: FragmentZadaniaBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentZadaniaBinding.inflate(inflater)
        binding.title.text = subject+"\nLista "+(number+1).toString()
        return binding.root
        //return inflater.inflate(R.layout.fragment_zadania, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView:RecyclerView=view.findViewById(R.id.recyclerViewZadania)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ZadaniaAdapter(DataList[number].exercises)
    }

}