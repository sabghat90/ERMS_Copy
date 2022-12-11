package com.kust.erms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kust.erms.R
import com.kust.erms.databinding.FragmentManagerSignUpBinding

class ManagerSignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
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
            findNavController().navigate(R.id.action_managerSignUpFragment_to_managerLoginFragment)
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
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(context, "User Added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}