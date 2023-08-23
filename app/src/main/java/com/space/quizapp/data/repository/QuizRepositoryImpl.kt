package com.space.quizapp.data.repository


import com.space.quizapp.data.local.dao.QuizDao
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoEntityMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDtoMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_point.QuizPointEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDomainMapper
import com.space.quizapp.data.remote.service.QuizApiService
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.utils.network.RequestHandler
import com.space.quizapp.utils.network.RequestResource


class QuizRepositoryImpl(
    private val quizDao: QuizDao,
    //mappers for quiz
    private val quizDtoDomainMapper: QuizDtoDomainMapper,
    private val quizDtoEntityMapper: QuizDtoEntityMapper,
    private val quizEntityDomainMapper: QuizEntityDomainMapper,
    private val quizPointEntityDomainMapper: QuizPointEntityDomainMapper,
    //mappers for quiz questions
    private val quizQuestionEntityDomainMapper: QuizQuestionEntityDomainMapper,
    private val quizQuestionEntityDtoMapper: QuizQuestionEntityDtoMapper,
    private val apiService: QuizApiService
) : QuizRepository, RequestHandler() {


    private var isInitialDataFetched = false

    //TODO INTERNET CHECK
    override suspend fun getQuiz(): RequestResource<List<QuizDomainModel>> {
        val existingData = quizDao.getQuiz()

        if (isInitialDataFetched) {
            // Return the existing data
            val existingDataDomainModels = existingData.map { quizEntityDomainMapper(it) }
            return RequestResource.success(existingDataDomainModels)
        }

        val data = apiCall { apiService.retrieveQuestions() }

        if (data.status == RequestResource.Status.SUCCESS) {
            val serverData = data.data ?: emptyList()

            if (serverData.isNotEmpty()) {
                // Insert the new data from the server
                serverData.forEach {
                    quizDao.insertQuiz(quizDtoEntityMapper(it))
                    quizDao.insertQuizQuestion(quizQuestionEntityDtoMapper(it))
                }

                // Update the flag to indicate that initial data has been fetched
                isInitialDataFetched = true

                // Return the new data from the server
                val newDataDomainModels = serverData.map { quizDtoDomainMapper(it) }
                return RequestResource.success(newDataDomainModels)
            }
        } else if (data.status == RequestResource.Status.ERROR) {
            return RequestResource.error(data.message ?: "Unknown error occurred", null)
        }

        return RequestResource.error("Failed to fetch data", null)
    }

    override suspend fun getQuizQuestion(subjectId: Int): List<QuizQuestionDomainModel> {
        return quizDao.getQuizQuestion(subjectId).map {
            quizQuestionEntityDomainMapper(it)
        }
    }

    override suspend fun getQuizByUserId(userId: String): List<QuizPointsDomainModel> {
            return quizDao.getSubjectsByUserId(userId).map {
                quizPointEntityDomainMapper(it)
            }
    }
}