package com.example.citigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citigo.databinding.ActivityTravelerProfileBinding

class TravelerProfileActivity : AppCompatActivity() {
    private var binding : ActivityTravelerProfileBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelerProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.startjourneybot?.setOnClickListener(){
            val intent = Intent(this, TravelerJourneyBot::class.java)
            startActivity(intent)
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }
}