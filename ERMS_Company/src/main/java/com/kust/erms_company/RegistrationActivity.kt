package com.kust.erms_company

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        Navigation.findNavController(this, R.id.fragmentContainerView)
            .setGraph(R.navigation.registration_nav_graph)
    }
}