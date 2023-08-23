package com.space.quizapp.domain.usecase.current_user.save

interface SaveUserSessionUseCase {
    suspend fun saveUserSession(username: String)
}