package com.example.lista2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.FragmentListyZadanBinding

class ListyZadan : Fragment() {

    private lateinit var binding: FragmentListyZadanBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListyZadanBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView:RecyclerView=view.findViewById(R.id.recyclerViewListyZadan)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = WordListAdapter(DataList,parentFragmentManager)
    }

    fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }







}