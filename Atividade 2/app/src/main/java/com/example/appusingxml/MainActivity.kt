package com.example.appusingxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appusingxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val texto = binding.inputBomDia.text.toString()
            val textoEscrito = binding.texto.text.toString()
            binding.texto.setText("$textoEscrito,$texto")

            binding.inputBomDia.setText("")
        }
    }
}