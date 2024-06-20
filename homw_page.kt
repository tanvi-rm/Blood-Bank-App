package com.example.lifeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.lifeshare.databinding.ActivityHomwPageBinding
import com.google.firebase.firestore.FirebaseFirestore

class homw_page : AppCompatActivity() {


    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var binding: ActivityHomwPageBinding
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomwPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseFirestore = FirebaseFirestore.getInstance()

        replaceFragement(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
             when (it.itemId){

                 R.id.home_icon -> replaceFragement(Home())
                 R.id.donate_icon -> replaceFragement(Donate_blood())
                 R.id.request_icon -> replaceFragement(Request_blood())
                 R.id.profile -> replaceFragement(Profile())

                 else -> {

                 }
             }
            true
        }

            }

    private fun replaceFragement(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }
        }



