package com.kust.erms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ManagerLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_manager_login, container, false)

        val btnLogin : Button = view.findViewById(R.id.btn_login)
        val btnSignUp : Button = view.findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_managerLoginFragment_to_managerSignUpFragment)
        }

        return view
    }
}