package com.example.lifeshare

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Home : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val intent1 = Intent(activity, hospitals::class.java)

        val hosptl = view.findViewById<ImageView>(R.id.hospital)
        hosptl.setOnClickListener {
            startActivity(intent1)
        }

        val intent4 = Intent(activity, Contributers::class.java)

        val contribute = view.findViewById<ImageView>(R.id.hospital1)
        contribute.setOnClickListener {
            startActivity(intent4)
        }

        //for blood banks
        val intent2 = Intent(activity, blood_banks::class.java)

        val bldbnk = view.findViewById<ImageView>(R.id.bloodbank)
        bldbnk.setOnClickListener {
            startActivity(intent2)
        }

        //for contribute
        val intent3 = Intent(activity, contri_page::class.java)

        val con = view.findViewById<ImageView>(R.id.contribute)
        con.setOnClickListener {
            startActivity(intent3)
        }

        firebaseFirestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        val userId = auth.currentUser!!.uid


        userId?.let {
            val documentReference = firebaseFirestore.collection("UserForm").whereEqualTo(
                "Email",
                auth.currentUser!!.email
            )
            documentReference.get()
                .addOnSuccessListener { querySnapshot ->
                    for (documentSnapshot in querySnapshot.documents) {
                        val data = documentSnapshot.data
                        val name = data?.get("FirstName").toString() + " " + data?.get("LastName")
                            .toString()
                        val bg = data?.get("BloodGroup")
                        view.findViewById<TextView>(R.id.currentUser).text = name
                        view.findViewById<TextView>(R.id.bloodGroup).text = bg as CharSequence?
                    }
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }}

            return view
        }
    }
