package com.kust.erms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kust.erms.databinding.FragmentSelectRoleBinding

class SelectRoleFragment : Fragment() {

    private var _binding: FragmentSelectRoleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectRoleBinding.inflate(inflater, container, false)


        binding.cardManager.setOnClickListener {
            findNavController().navigate(R.id.action_selectRoleFragment_to_managerLoginFragment)
        }

        binding.cardEmployee.setOnClickListener {
            findNavController().navigate(R.id.action_selectRoleFragment_to_employeeLoginFragment)
        }

        binding.cardCompany.setOnClickListener {
            findNavController().navigate(R.id.action_selectRoleFragment_to_companyLoginFragment)
        }

        return binding.root

    }
}