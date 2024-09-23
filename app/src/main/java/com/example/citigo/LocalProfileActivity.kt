package com.example.citigo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citigo.databinding.ActivityLocalProfileBinding

class LocalProfileActivity : AppCompatActivity() {
    private var binding : ActivityLocalProfileBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)



    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }
}