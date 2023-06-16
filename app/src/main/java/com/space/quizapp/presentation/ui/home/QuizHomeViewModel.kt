package com.space.quizapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCase
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewModelScope

/**
 * QuizHomeViewModel is responsible for handling the home screen of the user
 * It displays the gpa of the user
 *
 */
class QuizHomeViewModel(
    private val getUserPointsUseCase: GetUserPointsUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase,
    private val clearUserSessionUseCase: ClearUserSessionUseCase
) : ViewModel() {

    private val _userPoints = MutableLiveData<Double?>()
    val userPoints: LiveData<Double?> get() = _userPoints

    private val _session = MutableLiveData<String>()
    val session: LiveData<String?> get() = _session

    private suspend fun getCurrentUserSession():Result<String> = getUserSessionUseCase.invoke()

    fun updateSession(){
        viewModelScope{
            getCurrentUserSession().getOrNull()?.let {
                _session.value = it
            }
        }
    }

    fun clearUserSession(navController: NavController,directions: NavDirections){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            navigateTo(navController,directions)
        }
    }

    fun loadUserPoints(username: String) {
        viewModelScope {
            val points = getUserPointsUseCase.getUserPoints(username)
            _userPoints.value = points
        }
    }
    //TODO BETTER NAVIGATION IS IN THE NEXT PR

    fun navigateTo(navController: NavController,directions: NavDirections){
        navigateSafe(navController,directions)
    }
}