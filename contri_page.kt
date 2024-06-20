package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lifeshare.databinding.ActivityContriPageBinding
import com.example.lifeshare.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class contri_page : AppCompatActivity() {

    private lateinit var binding: ActivityContriPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContriPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val btn = findViewById<Button>(R.id.pay)

        binding.Arrow2.setOnClickListener {
            val intent = Intent(this, homw_page::class.java)
            startActivity(intent)
        }

        binding.pay.setOnClickListener {
            val intent1 = Intent(this, gpay::class.java)
            startActivity(intent1)
        }
    }
}