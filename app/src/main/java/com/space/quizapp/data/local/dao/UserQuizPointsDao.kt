package com.space.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.space.quizapp.data.local.model.entity.UserQuizPointsEntity

@Dao
interface UserQuizPointsDao {
    @Query("SELECT * FROM user_quiz_points WHERE userId = :userId AND subjectId = :subjectId")
    suspend fun getUserQuizPoints(userId: String, subjectId: Int): UserQuizPointsEntity?

    @Query("SELECT * FROM user_quiz_points WHERE userId = :userId")
    suspend fun getUserStats(userId: String):UserQuizPointsEntity

    @Insert
    suspend fun insertUserQuizPoints(userQuizPoints: UserQuizPointsEntity)

    @Update
    suspend fun updateUserQuizPoints(userQuizPoints: UserQuizPointsEntity)

}