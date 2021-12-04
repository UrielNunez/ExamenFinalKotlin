package com.example.alumnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.alumnos.Login.AccountActivity
import com.example.alumnos.Login.SignInActivity
import com.example.alumnos.databinding.ActivityMainBinding
import com.example.alumnos.databinding.ActivitySignInBinding
import com.example.alumnos.materias.DetailsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.accountButton.setOnClickListener {
            intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        val arrayAdapter:ArrayAdapter<*>
        val materias = mutableListOf("Modelos Abstractos", "Android", "Procesamiento de imágenes")
        val lvDatos = findViewById<ListView>(R.id.lvDatos)

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, materias)
        lvDatos.adapter = arrayAdapter

        binding.lvDatos.setOnItemClickListener(){ parent, view, position, id ->
            Toast.makeText(baseContext, parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show()
            intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}