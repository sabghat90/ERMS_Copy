@file:Suppress("DEPRECATION")

package com.kust.erms_company.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kust.erms_company.R
import com.kust.erms_company.databinding.FragmentCompanyLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyLoginFragment : Fragment() {

    private var _binding: FragmentCompanyLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompanyLoginBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_companyLoginFragment_to_registerCompanyFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}