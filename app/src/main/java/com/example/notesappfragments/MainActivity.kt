package com.example.notesappfragments
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesappfragments.databinding.MainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }


}