package com.example.citigo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.citigo.databinding.ActivityTravelerJourneyBotBinding
import com.vanniktech.emoji.EmojiPopup
import android.Manifest
import android.app.ActionBar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridView
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider

class TravelerJourneyBot : AppCompatActivity() {
    private var binding : ActivityTravelerJourneyBotBinding ?= null
    private lateinit var emojiPopup: EmojiPopup
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200

    // Define the ActivityResultLauncher
    private val speechToTextLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val resultText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding?.typeMsg?.setText(resultText?.get(0) ?: "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelerJourneyBotBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Initialize EmojiManager
        EmojiManager.install(GoogleEmojiProvider())
        // Set up the toolbar
        setSupportActionBar(binding?.TravelJourneyBotToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        // Initialize the EmojiPopup directly with the EditText
        emojiPopup = EmojiPopup.Builder.fromRootView(binding?.root ?: return)
            .setOnEmojiPopupShownListener {
                // Adjust the height when the popup is shown
                adjustEmojiPopupHeight()
            }
            .build(binding!!.typeMsg)

        // Show or hide the emoji popup when the emoji button is clicked
        binding?.emojiBtnBot?.setOnClickListener {
            emojiPopup.toggle()
        }


        // Check and request RECORD_AUDIO permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_RECORD_AUDIO_PERMISSION
            )
        }

        // Implement Speech-to-Text functionality
        binding?.micSpeechToText?.setOnClickListener {
            startSpeechToText()
        }



    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() // Handle the back button press
        return true
    }
    private fun startSpeechToText() {
        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
        }

        // Launch the activity for speech recognition
        speechToTextLauncher.launch(speechIntent)
    }

    companion object {
        private const val REQUEST_CODE_SPEECH_INPUT = 100
    }

    private fun adjustEmojiPopupHeight() {
        // Use reflection to access the popup's internal view and adjust its height
        try {
            val popupView = emojiPopup.javaClass.getDeclaredField("popupView")
            popupView.isAccessible = true
            val view = popupView.get(emojiPopup) as ViewGroup

            // Set the maximum height
            view.layoutParams.height = ActionBar.LayoutParams.WRAP_CONTENT // or a specific height
            view.requestLayout()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Handle permission request result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission was granted
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Permission for audio recording is required", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clear the binding to prevent memory leaks
    }

}