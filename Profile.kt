package com.example.lifeshare

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Profile : Fragment() {

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        firebaseFirestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val firstNameOfPerson = rootView.findViewById<TextView>(R.id.firstNameOfUser)
        val lastNameOfPerson = rootView.findViewById<TextView>(R.id.lastNameOfUser)
        val bloodGroupOfPerson = rootView.findViewById<TextView>(R.id.bloodGroupOfUser)
        val emailaddress = rootView.findViewById<TextView>(R.id.emailadd)
        val Dateofbirth = rootView.findViewById<TextView>(R.id.DOB1)
        val Phonenumber = rootView.findViewById<TextView>(R.id.phonenum)
        val Gender = rootView.findViewById<TextView>(R.id.gend)

        val userId = auth.currentUser?.uid

        userId?.let {
            val documentReference = firebaseFirestore.collection("UserForm").whereEqualTo("Email", auth.currentUser?.email)
            documentReference.get()
                .addOnSuccessListener { querySnapshot ->
                    for (documentSnapshot in querySnapshot.documents) {
                        val data = documentSnapshot.data
                        val fname = data?.get("FirstName")
                        firstNameOfPerson.text = fname as? CharSequence
                        val lname = data?.get("LastName")
                        lastNameOfPerson.text = lname as CharSequence?
                        val bloodgroup = data?.get("BloodGroup")
                        bloodGroupOfPerson.text = bloodgroup as CharSequence?
                        val emailadd = data?.get("Email")
                        emailaddress.text = emailadd as CharSequence?
                        val phne = data?.get("Phone_Number")
                        Phonenumber.text = phne as CharSequence?
                        val dob = data?.get("Date_Of_Birth")
                        Dateofbirth.text = dob as CharSequence?
                        val Gend = data?.get("gender")
                        Gender.text = Gend as CharSequence?
                    }
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }
        }

        val logoutButton = rootView.findViewById<TextView>(R.id.logout)
        logoutButton.setOnClickListener {
            LogOutUser()
        }

        return rootView
    }

    private fun LogOutUser() {
        Firebase.auth.signOut()
        val intent = Intent(requireContext(), Sign_in::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Profile()
    }
}
