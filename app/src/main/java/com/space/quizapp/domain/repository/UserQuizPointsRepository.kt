package com.space.quizapp.domain.repository


interface UserQuizPointsRepository {
    suspend fun insertUserQuizPoints(userId: String, subjectId: Int, quizPoints: Double)
}