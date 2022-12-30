package com.kust.erms_company.data.repository

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.kust.erms_company.data.model.Company
import com.kust.erms_company.util.FireStoreCollectionConstants
import com.kust.erms_company.util.SharedPrefConstants
import com.kust.erms_company.util.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val appPreferences: SharedPreferences,
    private val gson: Gson,
) : AuthRepository {

    override suspend fun registerCompany(
        email: String,
        password: String,
        company: Company,
        result: (UiState<String>) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    company.id = it.result.user?.uid ?: ""
                    CoroutineScope(Dispatchers.IO).launch {
                        updateCompanyInfo(company) { state ->
                            when (state) {
                                is UiState.Success -> {
                                    storeSession(id = it.result.user?.uid ?: "") {
                                        if (it == null) {
                                            result.invoke(UiState.Failure("Company register successfully but session failed to store"))
                                        } else {
                                            result.invoke(
                                                UiState.Success("Company register successfully!")
                                            )
                                        }
                                    }
                                }
                                is UiState.Failure -> {
                                    result.invoke(UiState.Failure(state.error))
                                }
                                else -> {

                                }
                            }
                        }
                    }
                } else {
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        result.invoke(UiState.Failure("Authentication failed, Invalid email entered"))
                    } catch (e: FirebaseAuthUserCollisionException) {
                        result.invoke(UiState.Failure("Authentication failed, Email already registered."))
                    } catch (e: Exception) {
                        result.invoke(UiState.Failure(e.message))
                    }
                }
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override suspend fun updateCompanyInfo(company: Company, result: (UiState<String>) -> Unit) {
        val document =
            fireStore.collection(FireStoreCollectionConstants.COMPANY).document(company.id)
        document
            .set(company)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Company has been update successfully")
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

    override suspend fun loginCompany(
        email: String,
        password: String,
        result: (UiState<String>) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storeSession(id = task.result.user?.uid ?: "") {
                        if (it == null) {
                            result.invoke(UiState.Failure("Failed to store local session"))
                        } else {
                            result.invoke(UiState.Success("Login successfully!"))
                        }
                    }
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email and password"))
            }
    }

    override suspend fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Email has been sent"))

                } else {
                    result.invoke(UiState.Failure(task.exception?.message))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email"))
            }
    }

    override suspend fun logout(result: () -> Unit) {
        auth.signOut()
        appPreferences.edit().putString(SharedPrefConstants.COMPANY_SESSION, null).apply()
        result.invoke()
    }

    override fun storeSession(id: String, result: (Company?) -> Unit) {
        fireStore.collection(FireStoreCollectionConstants.COMPANY).document(id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result.toObject(Company::class.java)
                    appPreferences.edit()
                        .putString(SharedPrefConstants.COMPANY_SESSION, gson.toJson(user)).apply()
                    result.invoke(user)
                } else {
                    result.invoke(null)
                }
            }
            .addOnFailureListener {
                result.invoke(null)
            }
    }

    override fun getSession(result: (Company?) -> Unit) {
        val companyStr = appPreferences.getString(SharedPrefConstants.COMPANY_SESSION, null)
        if (companyStr == null) {
            result.invoke(null)
        } else {
            val user = gson.fromJson(companyStr, Company::class.java)
            result.invoke(user)
        }
    }
}