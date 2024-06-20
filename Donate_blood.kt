package com.example.lifeshare

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifeshare.databinding.FragmentDonateBloodBinding
import com.google.firebase.firestore.FirebaseFirestore

class Donate_blood: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentDonateBloodBinding
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonateBloodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        userArrayList = ArrayList()


        myAdapter = MyAdapter(userArrayList,requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = myAdapter

        db = FirebaseFirestore.getInstance()
        fetchDataFromFirestore()
        val cardView = view.findViewById<CardView>(R.id.card)
        cardView?.setOnClickListener {

        }
    }

    private fun fetchDataFromFirestore() {
        db.collection("PatientForm")
            .get()
            .addOnSuccessListener { querySnapshot ->
                userArrayList.clear()

                for (document in querySnapshot) {
                    val firstName = document.getString("FirstName") ?: ""
                    val lastName = document.getString("LastName") ?: ""
                    val units = document.getString("BloodUnit") ?: ""
                    val date = document.getString("Required Date") ?: ""
                    val bloodGroup = document.getString("BloodGroup") ?: ""
                    val location = document.getString("Location") ?: ""
                    val phonenumber = document.getString("Phone_Number") ?: ""
                    val user = User(firstName, lastName, units, date, bloodGroup, location, phonenumber)
                    userArrayList.add(user)
                }

                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
            }
    }
}
