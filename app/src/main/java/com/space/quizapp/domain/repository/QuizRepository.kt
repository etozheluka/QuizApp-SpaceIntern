package com.space.quizapp.domain.repository

import com.space.quizapp.data.local.model.entity.QuizSubjectEntity
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.utils.network.RequestResource


interface QuizRepository {
    suspend fun getQuiz(): RequestResource<List<QuizDomainModel>>
    suspend fun getQuizQuestion(subjectId:Int): List<QuizQuestionDomainModel>
    suspend fun getQuizByUserId(userId:String): List<QuizPointsDomainModel>
}