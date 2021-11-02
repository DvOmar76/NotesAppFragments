package com.example.notesappfragments.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesappfragments.Models.MainViewModel
import com.example.notesappfragments.Models.Note
import com.example.notesappfragments.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment() {
    lateinit var binding: FragmentUpdateNoteBinding
    val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         binding=FragmentUpdateNoteBinding.inflate(inflater,container,false)
            binding.tvUpdate.text= noteOb.note
            binding.btnUpdate.setOnClickListener {

                val note=binding.edUpdate.text
                if (note.toString().isNotEmpty()){
                    binding.tvUpdate.text= note
                    mainViewModel.updateNote(noteOb, note.toString())
                    note.clear()
                }
                else
                {
                    Toast.makeText(requireContext(), "pleas enter text", Toast.LENGTH_SHORT).show()
                }

            }
        return binding.root

    }

    companion object {

        lateinit var noteOb: Note
    }
}