package com.kust.erms_company.data.repository

import com.kust.erms_company.data.model.Company
import com.kust.erms_company.util.UiState

interface AuthRepository {
    suspend fun registerCompany(
        email: String,
        password: String,
        company: Company,
        result: (UiState<String>) -> Unit,
    )

    suspend fun updateCompanyInfo(company: Company, result: (UiState<String>) -> Unit)
    suspend fun loginCompany(email: String, password: String, result: (UiState<String>) -> Unit)
    suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    suspend fun logout(result: () -> Unit)
    fun storeSession(id: String, result: (Company?) -> Unit)
    fun getSession(result: (Company?) -> Unit)
}