package com.space.quizapp.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_quiz_points")
data class UserQuizPointsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val userId: String, // Foreign key referencing the User table's user_id
    val subjectId: Int, // Foreign key referencing the QuizSubjectEntity's id
    var quizPoints: Double
)