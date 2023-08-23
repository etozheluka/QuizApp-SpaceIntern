package com.space.quizapp.data.local.model.mapper.quiz

import com.space.quizapp.data.remote.model.QuizDtoItem
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizDtoDomainMapper(
) : ModelMapper<QuizDtoItem, QuizDomainModel> {
    override fun invoke(model: QuizDtoItem): QuizDomainModel =
        QuizDomainModel(
            id = model.id,
            image = model.image,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle
        )
}


