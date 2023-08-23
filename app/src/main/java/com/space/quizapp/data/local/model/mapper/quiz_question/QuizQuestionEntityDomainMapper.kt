package com.space.quizapp.data.local.model.mapper.quiz_question

import com.space.quizapp.data.local.model.entity.QuizQuestionEntity
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizQuestionEntityDomainMapper :
    ModelMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override fun invoke(model: QuizQuestionEntity): QuizQuestionDomainModel {

        val answersUI = model.data.map { answer ->
            QuizQuestionDomainModel.Answer(
                answer = answer.answer,
                lastQuestion = answer.lastQuestion
            )
        }

        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            data = answersUI,
            point = model.point,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex,
            subjectId = model.subjectId
        )
    }
}
