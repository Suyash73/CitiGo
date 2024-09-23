package com.example.citigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.citigo.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.localProfile?.setOnClickListener(){
            // TODO
            val intent = Intent(this, LocalProfileActivity::class.java)
            startActivity(intent)
        }

        binding?.travellerProfile?.setOnClickListener(){
            // TODO
            val intent = Intent(this, TravelerProfileActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }
}