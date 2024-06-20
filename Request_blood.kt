package com.example.lifeshare

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifeshare.databinding.FragmentRequestBloodBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class Request_blood : Fragment() {

    private lateinit var binding: FragmentRequestBloodBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore
    private lateinit var gend: String
    lateinit var onSwitch: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestBloodBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = FirebaseAuth.getInstance()

        val bloodGroup = arrayOf("BloodGroup","A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        val spinnerr: Spinner = view.findViewById(R.id.bg)
        val adapterr: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bloodGroup)
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerr.adapter =  adapterr


        val units = arrayOf("Units","1", "2", "3", "4", "5", "6", "7", "8", "9")
        val spinner4: Spinner = view.findViewById(R.id.spinnerrrr)
        val adapter4: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, units)
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = adapter4

        val bloodLoaction = arrayOf("Location", "District Hospital (Ratnagiri)", "Chirayu Hospital", "Red Cross (Ratnagiri)", "Navjivan Blood Bank (thane)", "Kai. Wamanrao Oak Raktapedhi (Thane)", "Vaidya Blood Bank (Thane)")
        val spinner3: Spinner = view.findViewById(R.id.locate)
        val adapter3: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, bloodLoaction)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = adapter3

        val gen = view.findViewById<RadioGroup>(R.id.gender1)
        //gender
        val male = view.findViewById<RadioButton>(R.id.malee)
        val female = view.findViewById<RadioButton>(R.id.femalee1)
        val other = view.findViewById<RadioButton>(R.id.otherr1)

        gen.setOnCheckedChangeListener { radioGroup, checkedId ->
            gend = when (checkedId) {
                R.id.malee -> male.text.toString().trim()
                R.id.femalee1 -> female.text.toString().trim()
                R.id.otherr1 -> other.text.toString().trim()
                else -> ""
            }
        }

        val submt_btn = view.findViewById<MaterialButton>(R.id.submit)

        submt_btn.setOnClickListener {

            NotificationUtil.generateNotification(
                requireContext(),
                "Notification Title",
                "Notification Message"
            )

        }

        val dobTextView = view.findViewById<TextView>(R.id.dateeee)

        dobTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "${selectedDay}-${selectedMonth + 1}-${selectedYear}"
                    dobTextView.text = "$selectedDate"
                },
                year, month, day
            )

            datePickerDialog.show()
        }

        val dateTextView1 = view.findViewById<TextView>(R.id.dob)

        dateTextView1.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "${selectedDay}-${selectedMonth + 1}-${selectedYear}"
                    dateTextView1.text = "$selectedDate"
                },
                year, month, day
            )

            datePickerDialog.show()
        }


        submt_btn.setOnClickListener {
            val firstname = binding.fname.text.toString().trim()
            val lastname = binding.lname.text.toString().trim()
            val email = binding.emailll.text.toString().trim()
            val phone_no = binding.phone.text.toString().trim()
            val date_ob = binding.dateeee.text.toString().trim()
            val req_date = binding.dob.text.toString().trim()
            val mySwitch = view.findViewById<Switch>(R.id.switch3)


            if (email.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && phone_no.isNotEmpty() && date_ob.isNotEmpty() && gend.isNotEmpty()) {
                val FL = "$firstname $lastname"
                val FirstLast = FL.trim()

                val selectedBloodGroup = spinnerr.selectedItem.toString()
                val selectedBloodUnits = spinner4.selectedItem.toString()
                val location = spinner3.selectedItem.toString()


                val UserMap = hashMapOf(
                    "FirstName" to firstname,
                    "LastName" to lastname,
                    "Email" to email,
                    "Phone_Number" to phone_no,
                    "Date_Of_Birth" to date_ob,
                    "gender" to gend,
                    "Required Date" to req_date,
                    "BloodGroup" to selectedBloodGroup,
                    "BloodUnit" to selectedBloodUnits,
                    "Location" to location
                )

                db.collection("PatientForm").document(FirstLast).set(UserMap)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Request sent..", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(requireContext(), homw_page::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Try Again...", Toast.LENGTH_SHORT).show()
                    }

            } else {
                Toast.makeText(requireContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return view
    }
}