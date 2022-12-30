package com.kust.erms_company.data.repository

import com.kust.erms_company.data.model.Employee
import com.kust.erms_company.util.UiState

interface EmployeeRepository {

    suspend fun getEmployee(employee: Employee?, result: (UiState<List<Employee>>) -> Unit)
    suspend fun addEmployee(employee: Employee, result: (UiState<Pair<Employee, String>>) -> Unit)
    suspend fun updateEmployee(employee: Employee, result: (UiState<String>) -> Unit)
    suspend fun deleteEmployee(employee: Employee, result: (UiState<String>) -> Unit)
}