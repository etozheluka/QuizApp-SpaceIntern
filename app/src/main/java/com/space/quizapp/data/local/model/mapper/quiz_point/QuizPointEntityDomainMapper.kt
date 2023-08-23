package com.space.quizapp.data.local.model.mapper.quiz_point

import com.space.quizapp.data.local.model.entity.QuizPointsEntity
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizPointEntityDomainMapper:ModelMapper<QuizPointsEntity,QuizPointsDomainModel> {
    override fun invoke(model: QuizPointsEntity): QuizPointsDomainModel {
        return QuizPointsDomainModel(
            id = model.id,
            image = model.image,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle,
            quizPoints = model.quizPoints
        )
    }
}