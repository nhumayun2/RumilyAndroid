package com.example.rumily.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// This creates a small database file named "user_session"
private val Context.dataStore by preferencesDataStore(name = "user_session")

class SessionManager(private val context: Context) {

    // Define the key (like the key in localStorage)
    companion object {
        val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    // 1. SAVE TOKEN (localStorage.setItem)
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // 2. GET TOKEN (localStorage.getItem)
    // "Flow<String?>" is like an Observable. It automatically updates if the token changes.
    val token: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]
        }

    // 3. LOGOUT (localStorage.removeItem)
    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}