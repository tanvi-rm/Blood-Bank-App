package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.example.lifeshare.databinding.ActivityPatientFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class patient_form : AppCompatActivity() {
    private lateinit var binding: ActivityPatientFormBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
    lateinit var gend: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bloodGroups = arrayOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        val spinner: Spinner = findViewById(R.id.bg1)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroups)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val units = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val spinner2: Spinner = findViewById(R.id.spinnerrrr)
        val adapter2: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroups)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val bloodLoaction = arrayOf("mumbai", "pune", "rtn")
        val spinner3: Spinner = findViewById(R.id.location)
        val adapter3: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodLoaction)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        val gen = findViewById<RadioGroup>(R.id.gender)
        //gender
        val male = findViewById<RadioButton>(R.id.malee)
        val female = findViewById<RadioButton>(R.id.femalee1)
        val other = findViewById<RadioButton>(R.id.otherr1)

        gen.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.malee) {
                gend = male.text.toString().trim()
            }

            if (checkedId == R.id.femalee1) {
                gend = female.text.toString().trim()
            }

            if (checkedId == R.id.otherr1) {
                gend = other.text.toString().trim()
            }
        }

        binding.submit.setOnClickListener {
            val firstname = binding.fname.text.toString().trim()
            val lastname = binding.lname.text.toString().trim()
            val email = binding.emailll.text.toString().trim()
            val phone_no = binding.phone.text.toString().trim()
            val date_ob = binding.dob.text.toString().trim()


            if (email.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && phone_no.isNotEmpty() && date_ob.isNotEmpty() && gen.isNotEmpty()) {


                val UserMap = hashMapOf(
                    "FirstName" to firstname,
                    "LastName" to lastname,
                    "Email" to email,
                    "Phone_Number" to phone_no,
                    "Date_Of_Birth" to date_ob,
                    "gender" to gend
                )

                db.collection("PatientForm").document().set(UserMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Request sent..", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, homw_page::class.java)
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