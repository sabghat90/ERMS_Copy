package com.kust.erms_company.data.model

import com.google.firebase.firestore.Exclude

data class Employee(
    @get:Exclude
    var id: String = "",
    val name: String = "",
    val phoneNo: String = "",
    val joiningDate: String = "",
    val address: String = "",
    val basicPay: String = "",
    val status: String = "",
    val category: String = "",
    val points: String = "",
)
