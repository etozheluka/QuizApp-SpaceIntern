package com.space.quizapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.space.quizapp.data.local.data_store.SessionDataStoreManager
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_preference"
)

private fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}

val sessionDataStoreModule = module {
    single { provideDataStore(get()) }
    single { SessionDataStoreManager(get()) }
}