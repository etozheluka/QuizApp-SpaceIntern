package com.space.quizapp.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/**
 * This class is used to handle the data store operations
 */

class SessionDataStoreManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUsernameValue(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun getUsernameValue(key: String, defaultValue: String = ""): Flow<String> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: defaultValue
        }
        return preferences
    }

     suspend fun clearUsernameValue(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }
}