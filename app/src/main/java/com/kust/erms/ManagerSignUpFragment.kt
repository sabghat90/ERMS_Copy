package com.kust.erms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.kust.erms.databinding.FragmentManagerSignUpBinding

class ManagerSignUpFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private var _binding: FragmentManagerSignUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentManagerSignUpBinding.inflate(inflater, container, false)


        auth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            signUpManager()
        }

        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signUpManager() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() {
                if (it.isSuccessful) {

                } else {
                    Log.d("Check", email)
                }
            }
    }
}