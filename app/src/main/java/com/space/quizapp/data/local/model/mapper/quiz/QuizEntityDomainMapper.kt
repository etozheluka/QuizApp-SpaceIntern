package com.space.quizapp.data.local.model.mapper.quiz

import com.space.quizapp.data.local.model.entity.QuizSubjectEntity
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class QuizEntityDomainMapper  : ModelMapper<QuizSubjectEntity, QuizDomainModel> {
    override fun invoke(model: QuizSubjectEntity): QuizDomainModel {
        return QuizDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            image = model.image,
        )
    }
}