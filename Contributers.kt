package com.example.lifeshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class Contributers : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User1>
    private lateinit var myAdapter1: MyAdapter1
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributers)

        recyclerView = findViewById(R.id.recycle)
        userArrayList = ArrayList()
        myAdapter1 = MyAdapter1(userArrayList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter1

        // Fetch data from Firebase Firestore
        db = FirebaseFirestore.getInstance()
        fetchDataFromFirestore()

                val backarrow = findViewById<FloatingActionButton>(R.id.backarrow2)

        backarrow.setOnClickListener {
            val intent1 = Intent(this, homw_page::class.java)
            startActivity(intent1)
        }

    }

    private fun fetchDataFromFirestore() {
        db.collection("Payment")
            .get()
            .addOnSuccessListener { querySnapshot ->
                userArrayList.clear()

                for (document in querySnapshot) {
                    val FullName = document.getString("FullName") ?: ""
                    val Amount = document.getString("Amount") ?: ""
                    val user1 = User1(FullName, Amount)
                    userArrayList.add(user1)
                }

                myAdapter1.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // Handle failure
            }
    }
}
