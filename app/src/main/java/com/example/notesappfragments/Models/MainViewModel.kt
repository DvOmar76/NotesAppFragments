package com.example.notesappfragments.Models

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel(application: Application):AndroidViewModel(application) {
    val app=application
    private val notes=MutableLiveData<List<Note>>()
     var db=Firebase.firestore

    fun getNotes():LiveData<List<Note>>{
        db.collection("Notes")
            .get()
            .addOnSuccessListener { outPut->
                val tempNote=ArrayList<Note>()
                for (note in outPut){
                    note.data.map {
                            (key,value)->
                        tempNote.add(Note(note.id,value.toString()))
                    }
                    notes.postValue(tempNote)
                }
            }
        return notes
    }
    fun addNote(note:String){
        if (note.isNotEmpty())
        {
            val map= hashMapOf(
                "note" to note
            )
           db.collection("Notes")
               .add(map)
               .addOnSuccessListener {
                   Toast.makeText(app, "note is added", Toast.LENGTH_SHORT).show()
                   getNotes()
               }
               .addOnFailureListener{
                   Toast.makeText(app, "error", Toast.LENGTH_SHORT).show()
               }
        }
        else
        {
            Toast.makeText(app, "please enter text", Toast.LENGTH_SHORT).show()
        }

    }
    fun updateNote(noteOb: Note, noteUpdate:String){
                    db.collection("Notes").document(noteOb.id).update("note", noteUpdate)
                    getNotes()
            Toast.makeText(app, "note is updated", Toast.LENGTH_SHORT).show()

    }


    fun deleteNote(id:String) {
        db.collection("Notes").document(id).delete()
        Toast.makeText(app, "item is deleted", Toast.LENGTH_SHORT).show()
        getNotes()
    }




}