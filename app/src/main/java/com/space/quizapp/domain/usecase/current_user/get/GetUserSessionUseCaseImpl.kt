package com.space.quizapp.domain.usecase.current_user.get

import com.space.quizapp.domain.repository.UserSessionRepository

class GetUserSessionUseCaseImpl(private val userRepository: UserSessionRepository):GetUserSessionUseCase{
    override suspend fun invoke(): Result<String> {
        return userRepository.getUserSession()
    }
}