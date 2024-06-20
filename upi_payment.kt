package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.lifeshare.databinding.ActivityAcceptFormBinding
import com.example.lifeshare.databinding.ActivityUpiPaymentBinding
import com.google.firebase.auth.FirebaseAuth

class upi_payment : AppCompatActivity() {
    private lateinit var binding: ActivityUpiPaymentBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpiPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Arrow2.setOnClickListener {
            val intent = Intent(this, gpay::class.java)
            startActivity(intent)

        }


        val intent1 = Intent(this, gpay_page::class.java)


        val gpay_image = findViewById<ImageView>(R.id.gpay)

        gpay_image.setOnClickListener {
            startActivity(intent1)
        }

        val intent2 = Intent(this, bhim_page::class.java)


        val bhim_image = findViewById<ImageView>(R.id.bhim)

        bhim_image.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, paytm_page::class.java)


        val paytm_image = findViewById<ImageView>(R.id.paytm)

        paytm_image.setOnClickListener {
            startActivity(intent3)
        }

        val intent4 = Intent(this, phonepe_page::class.java)


        val phonepe_image = findViewById<ImageView>(R.id.phonepe)

        phonepe_image.setOnClickListener {
            startActivity(intent4)
        }
    }
}