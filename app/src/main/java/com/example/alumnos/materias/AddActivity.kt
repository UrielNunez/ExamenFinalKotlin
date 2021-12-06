package com.example.alumnos.materias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alumnos.R
import com.example.alumnos.databinding.ActivityAddBinding
import com.example.alumnos.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private val database = Firebase.database
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myRef = database.getReference("materias")

        val name=nameEditText.text
        val date=dateEditText.text
        val hora=horaEditText.text
        val description=descriptionEditText.text
        val url=urlEditText.text

        saveButton.setOnClickListener {
            val materias = Materias(
                name.toString(),
                date.toString(),
                hora.toString(),
                description.toString(),
                url.toString())
            myRef.child(myRef.push().key.toString()).setValue(materias)
            finish()
        }
    }
}