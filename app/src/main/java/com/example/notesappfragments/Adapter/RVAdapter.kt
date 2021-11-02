package com.example.notesappfragments.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.Fragments.HomeFragment
import com.example.notesappfragments.Fragments.UpdateNoteFragment
import com.example.notesappfragments.Models.Note
import com.example.notesappfragments.R
import com.example.notesappfragments.databinding.ItemRowBinding

class RVAdapter(val fragment: HomeFragment, var notes :List<Note>):RecyclerView.Adapter<RVAdapter.RVHolder>() {
    class RVHolder(view: View):RecyclerView.ViewHolder(view){
        val binding= ItemRowBinding.bind(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {

       with(holder) {
           val id =notes[position].id
           val note =notes[position].note
           binding.textView.text=note

           binding.imageButton.setOnClickListener {
               fragment.mainViewModel.deleteNote(id)

           }

           binding.imageButton2.setOnClickListener {
                fragment.findNavController().navigate(R.id.action_homeFragment_to_updateNoteFragment)
                UpdateNoteFragment.noteOb=notes[position]
           }

        }
    }

    override fun getItemCount()=notes.size


}