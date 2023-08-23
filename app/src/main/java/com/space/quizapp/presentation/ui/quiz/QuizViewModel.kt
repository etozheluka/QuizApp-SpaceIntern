package com.space.quizapp.presentation.ui.quiz

import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.points.insert.InsertQuizPointsUseCase
import com.space.quizapp.domain.usecase.quiz.get.quiz_question.GetQuizQuestionUseCase
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.model.mapper.quiz.question.QuestionDomainUIMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope

class QuizViewModel(
    private val getQuizQuestionUseCase: GetQuizQuestionUseCase,
    private val mapper: QuestionDomainUIMapper,
    private val insertQuizPointsUseCase: InsertQuizPointsUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase
) : QuizBaseViewModel() {

    private val _quizData = MutableLiveData<List<QuizQuestionUIModel>>()
    val quizData get() = _quizData

    var points = 0
    var quizId = 0
    var lastQuestion = false

    fun fetchQuizData(id: Int) {
        viewModelScope {
            val quiz = getQuizQuestionUseCase.invoke(id).map {
                mapper(it)
            }
            _quizData.value = quiz
        }
    }
    fun markAsLastQuestion(){
        lastQuestion = true
    }
    fun incrementQuizId(){
        quizId++
    }

    fun updatePoints(isCorrectAnswer:Boolean,points:Int){
        if(isCorrectAnswer){
            this.points += points
        }
    }

    fun saveGpa(subjectId:Int,quizPoints:Double){
        viewModelScope {
            val userId = getUserSessionUseCase.invoke().getOrNull()
            insertQuizPointsUseCase.invoke(userId = userId!!,subjectId = subjectId,quizPoints = quizPoints)
        }
    }
}