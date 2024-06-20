package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.lifeshare.databinding.ActivityBhimPageBinding
import com.example.lifeshare.databinding.ActivityCardPaymentBinding
import com.example.lifeshare.databinding.ActivityGpayPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Card_payment : AppCompatActivity() {
    private lateinit var binding: ActivityCardPaymentBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Arrow2.setOnClickListener {
            val intent = Intent(this, gpay::class.java)
            startActivity(intent)
        }
        binding.gppay.setOnClickListener {
            val fullname = binding.name.text.toString().trim()
            val email = binding.emailaddress.text.toString().trim()
            val creditno = binding.upiidInput.text.toString().trim()
            val amount = binding.amnt.text.toString().trim()

            if (email.isNotEmpty() && fullname.isNotEmpty() && creditno.isNotEmpty()){
                val UserMap = hashMapOf(
                    "FullName" to fullname,
                    "Card Number" to creditno,
                    "Email" to email,
                    "Amount" to amount
                )

                db.collection("Payment").document().set(UserMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "done...", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, payment_success::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show()

                    }

            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}