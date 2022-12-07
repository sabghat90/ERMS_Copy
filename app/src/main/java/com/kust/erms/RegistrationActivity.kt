package com.kust.erms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.kust.erms.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    // --Commented out by Inspection (12/7/2022 8:57 AM):private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}