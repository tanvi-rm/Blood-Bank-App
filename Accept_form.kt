package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.lifeshare.databinding.ActivityAcceptFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Accept_form : AppCompatActivity() {
    private lateinit var binding: ActivityAcceptFormBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val db = Firebase.firestore
    private var cov: String = ""
    private var tatt: String = ""
    private var hiv: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Arrow1.setOnClickListener {
            val intent1 = Intent(this, homw_page::class.java)
            startActivity(intent1)

        }

        val covidd = findViewById<RadioGroup>(R.id.covid)
        val hivv = findViewById<RadioGroup>(R.id.hiv)
        val tattoo = findViewById<RadioGroup>(R.id.tatto)
        val coyes = findViewById<RadioButton>(R.id.coyes)
        val tano = findViewById<RadioButton>(R.id.tano)
        val hino = findViewById<RadioButton>(R.id.hino)

        covidd.setOnCheckedChangeListener { _, checkedId ->
            cov = findViewById<RadioButton>(checkedId).text.toString().trim()
        }

        tattoo.setOnCheckedChangeListener { _, checkedId ->
            tatt = findViewById<RadioButton>(checkedId).text.toString().trim()
        }

        hivv.setOnCheckedChangeListener { _, checkedId ->
            hiv = findViewById<RadioButton>(checkedId).text.toString().trim()
        }

        val acceptButton = binding.accept
        acceptButton.setOnClickListener {
            if (coyes.isChecked && tano.isChecked && hino.isChecked) {
                Toast.makeText(this, "Request Accepted", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, homw_page::class.java)
                startActivity(intent)
                finish()
                val userMap = hashMapOf(
                    "covid-19" to cov,
                    "tattoo" to tatt,
                    "hiv" to hiv
                )

                db.collection("Accepted Request Donors").add(userMap)
                    .addOnSuccessListener {
//                        Toast.makeText(this, "Request Accepted", Toast.LENGTH_SHORT).show()
//                        val intent = Intent(this, homw_page::class.java)
//                        startActivity(intent)
//                        finish() // Close the current activity after navigating
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Request is Rejected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
