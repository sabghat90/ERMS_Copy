package com.kust.erms_company.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.kust.erms_company.data.model.Employee
import com.kust.erms_company.util.FirebaseDatabaseConstants
import com.kust.erms_company.util.UiState

class EmployeeRepositoryImpl(
    private var database: FirebaseDatabase,
) : EmployeeRepository {

    override suspend fun getEmployee(
        employee: Employee?,
        result: (UiState<List<Employee>>) -> Unit,
    ) {
        val ref = database.reference
            .child(FirebaseDatabaseConstants.EMPLOYEE)
            .orderByChild("company_id")
            .equalTo(employee!!.id)

        ref.get()
            .addOnSuccessListener {
                val empList = arrayListOf<Employee>()
                for (item in it.children) {
                    val list = item.getValue(Employee::class.java)
                }
                result.invoke(
                    UiState.Success(empList)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override suspend fun addEmployee(
        employee: Employee,
        result: (UiState<Pair<Employee, String>>) -> Unit,
    ) {
        val ref = database.reference.child(FirebaseDatabaseConstants.EMPLOYEE).push()
        val uniqueKey = ref.key ?: "invalid"
        employee.id = uniqueKey

        ref
            .setValue(employee)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(employee, "Employee has been added."))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override suspend fun updateEmployee(employee: Employee, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEmployee(employee: Employee, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }
}