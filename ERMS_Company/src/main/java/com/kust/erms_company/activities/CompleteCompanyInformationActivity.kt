@file:Suppress("DEPRECATION")

package com.kust.erms_company.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kust.erms_company.databinding.ActivityCompleteCompanyInformationBinding
import com.kust.erms_company.models.Company

class CompleteCompanyInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompleteCompanyInformationBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var selectedImage: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompleteCompanyInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // hooks
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        dbReference = FirebaseDatabase.getInstance().getReference("Company")


        binding.companyLogo.setOnClickListener {
            uploadImage()
        }

        binding.btnSubmit.setOnClickListener {

            val name = binding.editTextCompanyName.text.toString()
            val regNo = binding.editTextCompanyRegno.text.toString()
            val country = binding.editTextCountry.text.toString()
            val otherDetail = binding.editTextOtherDetail.text.toString()

            when {
                name.isEmpty() -> {
                    binding.editTextCompanyName.error = "Name Should Not Empty"

                }
                regNo.isEmpty() -> {
                    binding.editTextCompanyRegno.error = "Reg No Should Not Empty"

                }
                country.isEmpty() -> {
                    binding.editTextCountry.error = "Reg No Should Not Empty"

                }
                otherDetail.isEmpty() -> {
                    binding.editTextOtherDetail.error = "Reg No Should Not Empty"

                }
                else -> {
                    val uid = auth.uid
                    val email = auth.currentUser?.email

                    val company = Company(
                        name = name,
                        regNo = regNo,
                        country = country,
                        otherDetails = otherDetail,
                        email = email!!,
                        uid = uid!!
                    )

                    dbReference
                        .child(regNo)
                        .setValue(company)
                        .addOnSuccessListener {
                            makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        }
                    makeText(this, "There", Toast.LENGTH_SHORT).show()


                }
            }
        }

    }

    private fun uploadImage() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        this.startActivityForResult(intent, 101)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null) {
                binding.companyLogo.setImageURI(data.data)
                selectedImage = data.data!!
            }
        }
    }

    private fun updateCompanyInfo() {
    }
}