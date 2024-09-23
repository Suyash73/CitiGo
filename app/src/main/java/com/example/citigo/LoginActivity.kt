package com.example.citigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citigo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.verifyOtpButton?.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }
}