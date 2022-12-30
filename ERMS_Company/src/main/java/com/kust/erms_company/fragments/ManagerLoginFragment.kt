package com.kust.erms_company.fragments

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
import com.kust.erms_company.activities.CompleteProfileManagerActivity
import com.kust.erms_company.databinding.FragmentManagerLoginBinding

class ManagerLoginFragment : Fragment() {

    private var _binding: FragmentManagerLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentManagerLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()



        binding.btnLogin.setOnClickListener {
            loginManager()
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_companyLoginFragment_to_registerCompanyFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loginManager() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) run {
                    val lunchProfileActivity =
                        Intent(requireActivity(), CompleteProfileManagerActivity::class.java)
                    startActivity(lunchProfileActivity)
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