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
import com.kust.erms.databinding.FragmentRegisterCompanyBinding

class RegisterCompanyFragment : Fragment() {

    private var _binding: FragmentRegisterCompanyBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterCompanyBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            registerCompanyToFirebase()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun registerCompanyToFirebase() {
        val email: String = binding.editTextEmail.text.toString()
        val password: String = binding.editTextPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Company Added!", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_registerCompanyFragment_to_companyLoginFragment)
                } else {
                    Toast.makeText(requireContext(), "Sorry", Toast.LENGTH_SHORT).show()
                }
            }
    }
}