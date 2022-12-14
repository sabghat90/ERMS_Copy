package com.kust.erms_employee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.kust.erms_employee.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnDatePicker.setOnClickListener {

            datePicker()

        }

    }

    private fun datePicker() {
        val builder = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select Date of Birth")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        builder.show(
            supportFragmentManager,
            "date_picker"
        )
    }
}