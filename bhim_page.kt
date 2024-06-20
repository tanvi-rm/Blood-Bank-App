package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.lifeshare.databinding.ActivityAcceptFormBinding
import com.example.lifeshare.databinding.ActivityBhimPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class bhim_page : AppCompatActivity() {
    private lateinit var binding: ActivityBhimPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBhimPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Arrow2.setOnClickListener {
            val intent1 = Intent(this, upi_payment::class.java)
            startActivity(intent1)
        }


        binding.bpay.setOnClickListener {
            val fullname = binding.name.text.toString().trim()
            val email = binding.emailaddress.text.toString().trim()
            val upiid = binding.upiidInput.text.toString().trim()
            val amount = binding.amnt.text.toString().trim()

            if (isEmailValid(email) && fullname.isNotEmpty() && upiid.isNotEmpty()){
                val UserMap = hashMapOf(
                    "FullName" to fullname,
                    "UPI ID" to upiid,
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
                if (!isEmailValid(email)) {
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Empty fields are not allowed !!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}