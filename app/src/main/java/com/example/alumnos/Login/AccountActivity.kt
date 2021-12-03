package com.example.alumnos.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.alumnos.R
import com.example.alumnos.databinding.ActivityAccountBinding
import com.example.alumnos.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signOutImageView.setOnClickListener {
            signOut()
        }

        updateUI()
    }

    private fun  updateUI(){
        val user = auth.currentUser
        if(user != null ){
            binding.emailTextView.text = user.email
            binding.nameTextView.text = user.displayName
            binding.nameEditText.setText(user.displayName)
            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.profile_photo)
                .into(binding.profileImageView)

            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.profile_photo)
                .into(binding.bgProfileImageView)

        }
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }


}