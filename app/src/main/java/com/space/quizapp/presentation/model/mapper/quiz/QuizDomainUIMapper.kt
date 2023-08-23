package com.space.quizapp.presentation.model.mapper.quiz

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizDomainUIMapper(
) : ModelMapper<QuizDomainModel, QuizUIModel> {
    override fun invoke(model: QuizDomainModel): QuizUIModel =
        QuizUIModel(
            id = model.id,
            image = model.image,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle
        )
}

