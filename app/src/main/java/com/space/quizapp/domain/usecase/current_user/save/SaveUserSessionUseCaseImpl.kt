package com.space.quizapp.domain.usecase.current_user.save

import com.space.quizapp.domain.repository.UserSessionRepository

class SaveUserSessionUseCaseImpl(private val userRepository: UserSessionRepository) :
    SaveUserSessionUseCase {
    override suspend fun saveUserSession(username: String) {
        userRepository.saveUserSession(username)
    }
}