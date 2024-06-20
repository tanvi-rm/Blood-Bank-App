package com.example.lifeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class payment_success : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        val button = findViewById<Button>(R.id.button)

        val intent = Intent(this, homw_page::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
    }
}