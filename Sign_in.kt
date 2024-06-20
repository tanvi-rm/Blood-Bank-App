package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.lifeshare.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Sign_in : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val progressBar = binding.progressBar

        val forgotpass = binding.forgotPassword

        forgotpass.setOnClickListener {
            val email = binding.emaill.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter your email and click on 'Forgot Password'", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Check inbox for password reset link. Also, check spam or junk folder.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Apologies, failed to send reset link. Try again later.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnCompleteListener {
                        progressBar.visibility = View.INVISIBLE
                    }
            }
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this, Register_page::class.java)
            startActivity(intent)
        }

        binding.signin.setOnClickListener {
            val email = binding.emaill.text.toString().trim()
            val pass = binding.password.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (isValidEmail(email)) {

                    val UserMap = hashMapOf(
                        "Email" to email,
                        "Password" to pass
                    )

                    db.collection("users").document(email).set(UserMap)
                        .addOnFailureListener { exception ->
                            // Failed to save user data
                            Toast.makeText(
                                this,
                                "Failed to save user data: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, homw_page::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    // Email is not valid
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed !!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, homw_page::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
