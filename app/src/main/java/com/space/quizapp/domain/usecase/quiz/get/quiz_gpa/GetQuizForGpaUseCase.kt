package com.space.quizapp.domain.usecase.quiz.get.quiz_gpa

import com.space.quizapp.domain.model.QuizPointsDomainModel

interface GetQuizForGpaUseCase {
    suspend fun invoke(userId:String):List<QuizPointsDomainModel>
}