package com.example.lifeshare

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.example.lifeshare.databinding.ActivityUserFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class user_form : AppCompatActivity() {
    private lateinit var binding: ActivityUserFormBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore
    lateinit var gend:String
    lateinit var cov:String
    lateinit var tatt:String
    lateinit var hiv:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserFormBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val bloodGroups = arrayOf("BloodGroup", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        val spinner: Spinner = findViewById(R.id.bg)

        firebaseAuth = FirebaseAuth.getInstance()

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodGroups)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        val bloodLoaction = arrayOf("Location", "Ratnagiri","Thane")
        val spinner2: Spinner = findViewById(R.id.locate)
        val adapter2: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, bloodLoaction)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        val gen = findViewById<RadioGroup>(R.id.gender)
        val covidd = findViewById<RadioGroup>(R.id.covid)
        val hivv = findViewById<RadioGroup>(R.id.hiv)
        val tattoo = findViewById<RadioGroup>(R.id.tatto)
        //gender
        val male=findViewById<RadioButton>(R.id.malee)
        val female=findViewById<RadioButton>(R.id.femalee1)
        val other=findViewById<RadioButton>(R.id.otherr1)
        //covid
        val yes=findViewById<RadioButton>(R.id.coyes)
        val no=findViewById<RadioButton>(R.id.cono)
        //tattoo
        val yes1=findViewById<RadioButton>(R.id.tayes)
        val no1=findViewById<RadioButton>(R.id.tano)
        //hiv
        val yes2=findViewById<RadioButton>(R.id.hiyes)
        val no2=findViewById<RadioButton>(R.id.hino)




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

        covidd.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.coyes) {
                cov = yes.text.toString().trim()
            }

            if (checkedId == R.id.cono) {
                cov = no.text.toString().trim()
            }
        }

        tattoo.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.tayes) {
                tatt = yes1.text.toString().trim()
            }

            if (checkedId == R.id.tano) {
                tatt = no1.text.toString().trim()
            }
        }

        hivv.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.hiyes) {
                hiv = yes2.text.toString().trim()
            }

            if (checkedId == R.id.hino) {
                hiv = no2.text.toString().trim()
            }
        }

        val dateTextView = findViewById<TextView>(R.id.dob)

        dateTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "${selectedDay}-${selectedMonth + 1}-${selectedYear}"
                    dateTextView.text = "$selectedDate"
                },
                year, month, day
            )

            datePickerDialog.show()
        }

        binding.submit.setOnClickListener {
            val firstname = binding.fname.text.toString().trim()
            val lastname = binding.lname.text.toString().trim()
            val email = binding.emailll.text.toString().trim()
            val phone_no = binding.phone.text.toString().trim()
            val date_ob = binding.dob.text.toString().trim()
            val selectedBloodGroup = spinner.selectedItem.toString()




            if (email.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && phone_no.isNotEmpty() && date_ob.isNotEmpty() && gen.isNotEmpty()) {

                val UserMap = hashMapOf(
                    "FirstName" to firstname,
                    "LastName" to lastname,
                    "Email" to email,
                    "Phone_Number" to phone_no,
                    "Date_Of_Birth" to date_ob,
                    "gender" to gend,
                    "covid-19" to cov,
                    "tattoo" to tatt,
                    "hiv" to hiv,
                    "BloodGroup" to selectedBloodGroup
                )
                db.collection("UserForm").document().set(UserMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "done...", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, homw_page::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Try Again...", Toast.LENGTH_SHORT).show()

                    }

            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }

        }}}