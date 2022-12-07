package com.kust.erms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.kust.erms.databinding.FragmentManagerLoginBinding

class ManagerLoginFragment : Fragment() {

    var _binding: FragmentManagerLoginBinding? = null
    val binding get() = _binding!!

    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentManagerLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            loginManager()

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

    }
}