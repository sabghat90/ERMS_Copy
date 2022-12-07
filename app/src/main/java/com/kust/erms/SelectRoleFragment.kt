package com.kust.erms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SelectRoleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_select_role, container, false)

        val cardManager : CardView = view.findViewById(R.id.card_manager)
        val cardEmployee: CardView = view.findViewById(R.id.card_employee)

        cardManager.setOnClickListener {
            findNavController().navigate(R.id.action_selectRoleFragment_to_managerLoginFragment)
        }

        cardEmployee.setOnClickListener {
            findNavController().navigate(R.id.action_selectRoleFragment_to_employeeLoginFragment)
        }

        return view

    }
}