package com.space.quizapp.domain.usecase.points.insert

interface InsertQuizPointsUseCase {
    suspend fun invoke(userId: String, subjectId: Int, quizPoints: Double)
}