package com.testing.exness

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val EMAIL_KEY = stringPreferencesKey("email")
        val PASSWORD_KEY = stringPreferencesKey("PASSWORD")

    }

    suspend fun saveUserData(password: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
            prefs[PASSWORD_KEY] = password
        }
    }

    suspend fun getUserData(email: String): User? {
        val prefs = context.dataStore.data.map { prefs ->
            val storedEmail = prefs[EMAIL_KEY]
            val storedPassword = prefs[PASSWORD_KEY]

            // Only return the user if the email matches
            if (storedEmail == email) {
                User(email, storedPassword ?: "")
            } else null
        }
        return prefs.firstOrNull()  // Return the first valid user or null
    }

    suspend fun deleteUserData() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

}

data class User(val email: String, val password: String)


