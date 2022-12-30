package com.kust.erms_company.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kust.erms_company.R
import com.kust.erms_company.data.model.Company
import com.kust.erms_company.databinding.FragmentRegisterCompanyBinding
import com.kust.erms_company.util.UiState
import com.kust.erms_company.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCompanyFragment : Fragment() {

    private var _binding: FragmentRegisterCompanyBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()

        binding.btnRegister.setOnClickListener {
            if (validation()) {
                authViewModel.register(
                    email = binding.editTextEmail.text.toString().trim(),
                    password = binding.editTextPassword.text.toString(),
                    company = companyObj()
                )
            }
        }
    }

    private fun observer() {
        authViewModel.register.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Failure -> {
                    binding.btnRegister.text = getString(R.string.register)
                    binding.progressBar.hide()
                    toast(state.error)
                }
                UiState.Loading -> {
                    binding.btnRegister.text = ""
                    binding.progressBar.show()
                }
                is UiState.Success -> {
                    binding.btnRegister.text = getString(R.string.register)
                    binding.progressBar.hide()
                    toast(state.data)
                    findNavController().navigate(R.id.action_registerCompanyFragment_to_companyLoginFragment)
                }
            }
        }
    }

    private fun companyObj(): Company {
        return Company(
            id = "",
            name = "",
            regNo = "",
            country = "",
            otherDetails = "",
            email = binding.editTextEmail.text.toString().trim()
        )
    }

    private fun validation(): Boolean {
        var isValid = true

        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            toast("Email required")
            binding.editTextEmail.requestFocus()
            binding.editTextEmail.error = "Email Required"
        }

        if (password.isEmpty()) {
            isValid = false
            toast("Enter Password")
            binding.editTextPassword.requestFocus()
            binding.editTextPassword.error = "Password Required"
        } else {
            if (password.length < 8) {
                isValid = false
                toast("Password should be at least 8 characters")
                binding.editTextPassword.requestFocus()
                binding.editTextPassword.error = "Password length should be 8 characters"
            }
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}