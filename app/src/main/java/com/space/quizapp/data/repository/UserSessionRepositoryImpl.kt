package com.space.quizapp.data.repository

import com.space.quizapp.data.local.data_store.SessionDataStoreManager
import com.space.quizapp.domain.repository.UserSessionRepository
import kotlinx.coroutines.flow.firstOrNull

class UserSessionRepositoryImpl(
    private val dataStoreManager: SessionDataStoreManager,
): UserSessionRepository{

    /**
     * This functions is used to handle the data store operations
     */
    override suspend fun getUserSession(): Result<String> {
        return Result.runCatching {
            val flow = dataStoreManager.getUsernameValue(STRING_KEY)
            flow.firstOrNull() ?: ""
        }
    }

    override suspend fun saveUserSession(user: String) {
        Result.runCatching {
            dataStoreManager.saveUsernameValue(STRING_KEY, user)
        }

    }

    override suspend fun clearUserSession() {
        Result.runCatching {
            dataStoreManager.clearUsernameValue(STRING_KEY)
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}