package com.example.alumnos.materias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.example.alumnos.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val key = intent.getStringExtra("key")
        val database = Firebase.database
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val myRef = database.getReference("videogame").child(
            key.toString()
        )

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val materias:Materias? = dataSnapshot.getValue(Materias::class.java)
                if (materias != null) {
                    nameEditText.text = Editable.Factory.getInstance().newEditable(materias.name)
                    dateEditText.text = Editable.Factory.getInstance().newEditable(materias.date)
                    horaEditText.text = Editable.Factory.getInstance().newEditable(materias.hora)
                    descriptionEditText.text = Editable.Factory.getInstance().newEditable(materias.description)
                    urlEditText.text = Editable.Factory.getInstance().newEditable(materias.url)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        saveButton.setOnClickListener { v ->

            val name : String = nameEditText.text.toString()
            val date : String = dateEditText.text.toString()
            val hora : String = horaEditText.text.toString()
            val description: String = descriptionEditText.text.toString()
            val url: String = urlEditText.text.toString()

            myRef.child("name").setValue(name)
            myRef.child("date").setValue(date)
            myRef.child("hora").setValue(hora)
            myRef.child("description").setValue(description)
            myRef.child("url").setValue(url)

            finish()
        }

    }
}