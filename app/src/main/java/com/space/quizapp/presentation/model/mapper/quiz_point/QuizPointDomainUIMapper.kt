package com.space.quizapp.presentation.model.mapper.quiz_point

import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.presentation.model.QuizPointsUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizPointDomainUIMapper:ModelMapper<QuizPointsDomainModel,QuizPointsUIModel> {
    override fun invoke(model: QuizPointsDomainModel): QuizPointsUIModel {
        return QuizPointsUIModel(
            id = model.id,
            image = model.image,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle,
            quizPoints = model.quizPoints
        )
    }
}