package com.space.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.model.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Insert
    suspend fun saveUser(username: UserEntity)

    @Query("SELECT (SUM(quizPoints) / (SELECT SUM(point) FROM quiz_question)) * 4.0 AS gpa FROM user_quiz_points WHERE userId = :userId")
    fun getQuizPointsForSubject(userId: String): Double
}