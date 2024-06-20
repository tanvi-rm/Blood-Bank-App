package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lifeshare.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Patterns

class Register_page : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signin.setOnClickListener {
            val intent = Intent(this, Sign_in::class.java)
            startActivity(intent)
        }

        binding.signup.setOnClickListener {
            val email = binding.emaill.text.toString().trim()
            val pass = binding.password.text.toString()
            val confirmPass = binding.retypePassword.text.toString()

            if (isEmailValid(email) && isValidPassword(pass) && pass == confirmPass) {
                // Email is valid, password is valid, and password matches confirmation

                val UserMap = hashMapOf(
                    "Email" to email,
                    "Password" to confirmPass
                )

                db.collection("Registered Users").document(email).set(UserMap)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Failed to save user data: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }

                firebaseAuth.createUserWithEmailAndPassword(email, confirmPass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, user_form::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Failed to create user: ${it.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                if (!isEmailValid(email)) {
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                } else if (!isValidPassword(pass)) {
                    Toast.makeText(this, "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show()
                } else if (pass != confirmPass) {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Empty fields are not allowed !!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
