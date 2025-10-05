package com.pampang.nav.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    val currentUser get() = firebaseAuth.currentUser

    suspend fun register(
        email: String,
        password: String,
        username: String,
        role: String
    ): Result<Unit> {
        return try {
            _isLoading.postValue(true)

            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("No UID found")

            val userData = mapOf(
                "uid" to uid,
                "email" to email,
                "username" to username,
                "role" to role,
                "createdAt" to System.currentTimeMillis()
            )

            firestore.collection("users").document(uid).set(userData).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            _isLoading.postValue(false)
        }
    }

    suspend fun login(email: String, password: String, role: String): Result<Unit> {
        return try {
            _isLoading.postValue(true)

            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("No UID found")

            val userDoc = firestore.collection("users").document(uid).get().await()
            val storedRole = userDoc.getString("role") ?: throw Exception("User role not found")

            if (storedRole != role) {
                throw Exception("Please double-check your credentials and selected role.")
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            _isLoading.postValue(false)
        }
    }


    fun logout() {
        firebaseAuth.signOut()
    }
}
