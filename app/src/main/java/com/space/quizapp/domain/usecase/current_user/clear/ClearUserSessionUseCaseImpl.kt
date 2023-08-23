package com.space.quizapp.domain.usecase.current_user.clear

import com.space.quizapp.domain.repository.UserSessionRepository

class ClearUserSessionUseCaseImpl(private val userRepository: UserSessionRepository): ClearUserSessionUseCase {

    override suspend fun invoke() {
        userRepository.clearUserSession()
    }
}