package com.example.rumily.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rumily.data.RetrofitClient
import com.example.rumily.data.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)

    // 1. Loading & Error
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    // 2. Success States
    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess = _registerSuccess.asStateFlow()

    // NEW: Verification Success State
    private val _verificationSuccess = MutableStateFlow(false)
    val verificationSuccess = _verificationSuccess.asStateFlow()

    // --- LOGIN ---
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val request = mapOf("email" to email, "password" to password)
                val response = RetrofitClient.instance.login(request)
                sessionManager.saveToken(response.token)
                _loginSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message ?: "Login failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // --- REGISTER ---
    fun register(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val request = mapOf("name" to name, "email" to email, "password" to password, "phoneNumber" to phone)
                RetrofitClient.instance.register(request)
                _registerSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message ?: "Registration failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // --- VERIFY EMAIL (NEW) ---
    fun verifyEmail(email: String, code: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val request = mapOf("email" to email, "code" to code)
                val response = RetrofitClient.instance.verifyEmail(request)

                // Usually verification returns a token (auto-login), so we save it.
                sessionManager.saveToken(response.token)

                _verificationSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message ?: "Verification failed"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Reset states so navigation doesn't loop
    fun resetStates() {
        _loginSuccess.value = false
        _registerSuccess.value = false
        _verificationSuccess.value = false
        _error.value = null
    }
}