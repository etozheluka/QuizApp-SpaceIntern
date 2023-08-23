package com.space.quizapp.data.local.model.mapper.quiz_question

import com.space.quizapp.data.remote.model.QuizDtoItem
import com.space.quizapp.data.local.model.entity.QuizQuestionEntity
import com.space.quizapp.utils.mapper.ModelMapper

class QuizQuestionEntityDtoMapper : ModelMapper<QuizDtoItem, List<QuizQuestionEntity>> {
    override fun invoke(model: QuizDtoItem): List<QuizQuestionEntity> {
        return model.questions.map { questionDto ->
            QuizQuestionEntity(
                questionTitle = questionDto.questionTitle,
                data = questionDto.answers.map { QuizQuestionEntity.Answer(
                    answer = it,
                    lastQuestion = questionDto.lastQuestion
                ) },
                point = questionDto.point,
                correctAnswer = questionDto.correctAnswer,
                questionIndex = questionDto.questionIndex,
                subjectId = model.id
            )
        }
    }
}