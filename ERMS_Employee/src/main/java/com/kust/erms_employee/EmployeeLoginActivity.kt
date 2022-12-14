@file:Suppress("DEPRECATION")

package com.kust.erms_employee

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kust.erms_employee.databinding.ActivityEmployeeLoginBinding

class EmployeeLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Logging in")
        progressDialog.setCancelable(false)

        binding.btnLogin.setOnClickListener {
            progressDialog.show()
            loginEmployee()
        }

        val user = auth.currentUser
        if (user != null) {
            Intent(this@EmployeeLoginActivity, ProfileActivity::class.java).apply {
                startActivity(this)
            }
            this.finishAffinity()
        } else {
            return
        }
    }

    private fun loginEmployee() {
        val email: String = binding.editTextEmail.text.toString()
        val password: String = binding.editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this@EmployeeLoginActivity, "Logged In", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.hide()
                } else {
                    Toast.makeText(
                        this@EmployeeLoginActivity,
                        "Email or Password Incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.hide()
                }
            }
    }
}