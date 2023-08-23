package com.space.quizapp.domain.usecase.quiz.get.quiz_question

import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.QuizRepository

class GetQuizQuestionUseCaseImpl(private val repository: QuizRepository):GetQuizQuestionUseCase{
    override suspend fun invoke(subjectId:Int): List<QuizQuestionDomainModel> {
        return repository.getQuizQuestion(subjectId)
    }
}