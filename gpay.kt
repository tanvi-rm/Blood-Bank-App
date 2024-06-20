package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.lifeshare.databinding.ActivityAcceptFormBinding
import com.example.lifeshare.databinding.ActivityGpayBinding
import com.google.firebase.auth.FirebaseAuth

class gpay : AppCompatActivity() {
    private lateinit var binding:ActivityGpayBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGpayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()



        val intent = Intent(this, upi_payment::class.java)

        val upi_text = findViewById<TextView>(R.id.upi_text)
        val upi_image = findViewById<ImageView>(R.id.upi_image)

        upi_text.setOnClickListener {
            startActivity(intent)
        }

        upi_image.setOnClickListener {
            startActivity(intent)
        }

        binding.Arrow2.setOnClickListener {
            val intent1 = Intent(this, contri_page::class.java)
            startActivity(intent1)

        }

        val intent1 = Intent(this, Card_payment::class.java)

        val card_text = findViewById<TextView>(R.id.card_text)
        val card_image = findViewById<ImageView>(R.id.card_image)

        card_text.setOnClickListener {
            startActivity(intent1)
        }

        card_image.setOnClickListener {
            startActivity(intent1)
        }
    }
}