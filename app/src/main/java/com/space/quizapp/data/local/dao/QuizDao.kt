package com.space.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.quizapp.data.local.model.entity.QuizPointsEntity
import com.space.quizapp.data.local.model.entity.QuizQuestionEntity
import com.space.quizapp.data.local.model.entity.QuizSubjectEntity

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_question where subjectId=:subjectId")
    suspend fun getQuizQuestion(subjectId:Int): List<QuizQuestionEntity>

    @Query("SELECT * FROM subjects")
    suspend fun getQuiz(): List<QuizSubjectEntity>

    @Query("SELECT subjects.id, subjects.image, subjects.quizDescription, subjects.quizTitle, user_quiz_points.quizPoints " +
            "FROM subjects " +
            "JOIN user_quiz_points ON subjects.id = user_quiz_points.subjectId " +
            "WHERE user_quiz_points.userId = :userId")
    fun getSubjectsByUserId(userId: String): List<QuizPointsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestion(quiz: List<QuizQuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizSubjectEntity)
}