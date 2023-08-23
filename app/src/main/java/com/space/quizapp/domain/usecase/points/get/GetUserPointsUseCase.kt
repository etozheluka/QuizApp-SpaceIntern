package com.space.quizapp.domain.usecase.points.get

interface GetUserPointsUseCase {
    suspend fun getUserPoints(userId: String): Double?
}