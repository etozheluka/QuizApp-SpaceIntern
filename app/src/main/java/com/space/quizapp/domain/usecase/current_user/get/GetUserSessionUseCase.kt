package com.space.quizapp.domain.usecase.current_user.get

interface GetUserSessionUseCase {
    suspend fun invoke(): Result<String>
}