package com.example.citigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citigo.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private var binding: ActivityWelcomeBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // welcome button action listener
        binding?.welcomeButton?.setOnClickListener(){
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }
}