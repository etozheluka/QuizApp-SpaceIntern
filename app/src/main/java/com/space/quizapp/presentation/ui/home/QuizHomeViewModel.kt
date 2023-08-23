package com.space.quizapp.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.points.get.GetUserPointsUseCase
import com.space.quizapp.domain.usecase.quiz.get.quiz.GetQuizUseCase
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.model.mapper.quiz.QuizDomainUIMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope
import com.space.quizapp.utils.network.RequestResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * QuizHomeViewModel is responsible for handling the home screen of the user
 * It displays the gpa of the user
 *
 */
class QuizHomeViewModel(
    private val getUserPointsUseCase: GetUserPointsUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase,
    private val clearUserSessionUseCase: ClearUserSessionUseCase,
    private val getQuizUseCase: GetQuizUseCase,
    private val domainToUiMapper: QuizDomainUIMapper,
) : QuizBaseViewModel() {

    private val _userPoints = MutableLiveData<Double?>()
    val userPoints get() = _userPoints

    private val _session = MutableLiveData<String>()
    val session get() = _session

    private val _quizData = MutableLiveData<List<QuizUIModel>?>()
    val quizData get() = _quizData

    private suspend fun getCurrentUserSession(): Result<String> = getUserSessionUseCase.invoke()

    fun updateSession() {
        viewModelScope {
            getCurrentUserSession().getOrNull()?.let {
                _session.value = it
            }
        }
    }

    fun fetchQuizData() {
        viewModelScope {

            val quiz = getQuizUseCase.invoke()
            quiz.status.let {
                when (it) {
                    RequestResource.Status.SUCCESS -> {
                        val quizList = quiz.data?.map {
                            domainToUiMapper(it)
                        }
                        _quizData.value = quizList
                    }
                    else -> setErrorValue(quiz.message.toString())
                }
            }
        }
    }

    fun clearUserSession() {
        viewModelScope {
            clearUserSessionUseCase.invoke()
            setNavigation(QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizLogInFragment())
        }
    }

    fun loadUserPoints(username: String) {
        viewModelScope {
            val points = withContext(Dispatchers.IO){
                getUserPointsUseCase.getUserPoints(username)
            }
            _userPoints.value = points
        }
    }
}