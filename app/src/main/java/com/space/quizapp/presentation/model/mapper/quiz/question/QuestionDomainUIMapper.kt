package com.space.quizapp.presentation.model.mapper.quiz.question


import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuestionDomainUIMapper:
    ModelMapper<QuizQuestionDomainModel, QuizQuestionUIModel> {
    override fun invoke(model: QuizQuestionDomainModel): QuizQuestionUIModel {

        val answersUI = model.data.map { answer ->
            QuizQuestionUIModel.Answer(
                answer = answer.answer,
                correctAnswer = model.correctAnswer,
                lastQuestion = answer.lastQuestion,
                point = model.point
            )
        }

        return QuizQuestionUIModel(
            subjectId = model.subjectId,
            questionTitle = model.questionTitle,
            data = answersUI,
            point = model.point,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
    }
}