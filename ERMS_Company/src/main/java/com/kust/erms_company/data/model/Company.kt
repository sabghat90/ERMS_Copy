package com.kust.erms_company.data.model

import com.google.firebase.firestore.Exclude

data class Company(
    @get:Exclude
    var id: String = "",
    val name: String?,
    val regNo: String?,
    val country: String?,
    val otherDetails: String?,
    val email: String?,
)
