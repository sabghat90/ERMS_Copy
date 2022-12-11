@file:Suppress("DEPRECATION")

package com.kust.erms_company.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kust.erms_company.R
import com.kust.erms_company.activities.CompleteCompanyInformationActivity
import com.kust.erms_company.databinding.FragmentCompanyLoginBinding

class CompanyLoginFragment : Fragment() {

    private var _binding: FragmentCompanyLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompanyLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setMessage("Logging In...")

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_companyLoginFragment_to_registerCompanyFragment)
        }

        binding.btnLogin.setOnClickListener {
            companyLogin()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun companyLogin() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        progressDialog.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) run {
                    val lunchProfileActivity =
                        Intent(requireActivity(), CompleteCompanyInformationActivity::class.java)
                    startActivity(lunchProfileActivity)
                    progressDialog.hide()
                    activity?.finish()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Email or Password Is Incorrect.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}