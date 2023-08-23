package com.space.quizapp.domain.repository

interface UserSessionRepository {
    //DataStore
    suspend fun getUserSession(): Result<String>
    suspend fun saveUserSession(user: String)
    suspend fun clearUserSession()
}