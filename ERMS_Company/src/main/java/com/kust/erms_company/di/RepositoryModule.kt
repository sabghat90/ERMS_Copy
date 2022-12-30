package com.kust.erms_company.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import com.kust.erms_company.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCompanyRepository(
        database: FirebaseFirestore,
        storageReference: StorageReference,
    ): CompanyRepository {
        return CompanyRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        fireStore: FirebaseFirestore,
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
        gson: Gson,
    ): AuthRepository {
        return AuthRepositoryImpl(auth, fireStore, appPreferences, gson)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        fireStore: FirebaseFirestore,
        database: FirebaseDatabase,
    ): EmployeeRepository {
        return EmployeeRepositoryImpl(database)
    }
}