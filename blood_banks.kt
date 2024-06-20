package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifeshare.databinding.ActivityBloodBanksBinding
import com.example.lifeshare.databinding.ActivityHospitalsBinding
import com.google.firebase.auth.FirebaseAuth

class blood_banks : AppCompatActivity() {
    private lateinit var binding: ActivityBloodBanksBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBloodBanksBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.backarrow2.setOnClickListener {
            val intent = Intent(this, homw_page::class.java)
            startActivity(intent)

        }
    }
}