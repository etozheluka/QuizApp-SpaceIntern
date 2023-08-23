package com.space.quizapp.presentation.ui.points


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.quiz.get.quiz_gpa.GetQuizForGpaUseCase
import com.space.quizapp.presentation.model.QuizPointsUIModel
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.model.mapper.quiz.QuizDomainUIMapper
import com.space.quizapp.presentation.model.mapper.quiz_point.QuizPointDomainUIMapper
import com.space.quizapp.presentation.model.mapper.user.UserUIDomainMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizPointsViewModel(
    private val clearUserSessionUseCase: ClearUserSessionUseCase,
    private val getQuizForGpaUseCase: GetQuizForGpaUseCase,
    private val quizPointDomainUIMapper: QuizPointDomainUIMapper,
) : QuizBaseViewModel() {


    private val _quizData = MutableLiveData<List<QuizPointsUIModel>>()
    val quizData get() = _quizData


    fun clearUserSession(){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            setNavigation(QuizPointsFragmentDirections.actionQuizPointsFragmentToQuizLogInFragment())
        }
    }

    fun getQuiz(userId: String) {
        viewModelScope {
            val quiz = withContext(Dispatchers.IO) {
                getQuizForGpaUseCase.invoke(userId).map {
                    quizPointDomainUIMapper(it)
                }
            }
            quizData.value = quiz
        }
    }
}