package com.kust.erms_company.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kust.erms_company.data.model.Company
import com.kust.erms_company.data.repository.AuthRepository
import com.kust.erms_company.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>>
        get() = _register

    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    private val _forgetPassword = MutableLiveData<UiState<String>>()
    val forgetPassword: LiveData<UiState<String>>
        get() = _forgetPassword

    fun register(
        email: String,
        password: String,
        company: Company,
    ) {
        _register.value = UiState.Loading

        CoroutineScope(Dispatchers.IO).launch {
            authRepository.registerCompany(
                email = email,
                password = password,
                company = company
            ) {
                _register.value = it
            }
        }
    }

    fun login(
        email: String,
        password: String,
    ) {
        _register.value = UiState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            authRepository.loginCompany(
                email = email,
                password = password
            ) {
                _register.value = it
            }
        }

    }

    fun forgetPassword(
        email: String,
    ) {
        _forgetPassword.value = UiState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            authRepository.forgotPassword(email) {
                _forgetPassword.value = it
            }
        }
    }
}