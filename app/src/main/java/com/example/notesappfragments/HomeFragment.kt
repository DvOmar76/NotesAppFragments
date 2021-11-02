package com.example.notesappfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappfragments.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    lateinit var binding: FragmentHomeBinding
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        mainViewModel.getNotes().observe(this,{
            binding.recyclerView.adapter=RVAdapter(this,it)
            binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
            binding.recyclerView.adapter?.notifyDataSetChanged()


        })


            binding.btnAddNote.setOnClickListener {
                val note=binding.edNote.text
                mainViewModel.addNote(note.toString())
                note.clear()
            }

        return binding.root
    }

}