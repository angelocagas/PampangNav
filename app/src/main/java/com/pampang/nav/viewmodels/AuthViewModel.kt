package com.pampang.nav.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pampang.nav.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
            result.onSuccess {
                // Handle success
            }.onFailure {
                // Handle failure
            }
        }
    }

    fun register(email: String, password: String, username: String, role: String) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty() || username.isEmpty() || role.isEmpty()) return@launch

            val result = authRepository.register(email, password, username, role)
            result.onSuccess {
                // Handle success
            }.onFailure {
                // Handle failure
            }
        }
    }

    fun logout() {
        authRepository.logout()
    }

    val currentUser = authRepository.currentUser
}
