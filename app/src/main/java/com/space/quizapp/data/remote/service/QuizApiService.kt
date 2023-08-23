package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("4d182215-c784-4e19-a883-26cf0b827328")
    suspend fun retrieveQuestions(): Response<List<QuizDtoItem>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}