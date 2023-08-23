package com.space.quizapp.presentation.ui.log_in

import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.save.SaveUserSessionUseCase
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.model.mapper.user.UserUIDomainMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.Resource
import com.space.quizapp.utils.extensions.viewModelScope

/**
 * QuizLogInViewModel is responsible for handling the log in and registration of the user
 */
class QuizLogInViewModel(
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val userUIDomainMapper: UserUIDomainMapper,
    private val saveUserSessionUseCase: SaveUserSessionUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase
    ) : QuizBaseViewModel() {


    private val _session = MutableLiveData<String>()
    val session get() = _session

    private suspend fun getCurrentUserSession():Result<String> = getUserSessionUseCase.invoke()

    /**
     * observeSession is responsible for observing the current user session
     */
    fun observeSession(){
        viewModelScope{
            getCurrentUserSession().getOrNull()?.let {
                _session.value = it
            }
        }
    }

    /**
     * authorizeUser is responsible for authorizing the user
     */
    fun authorizeUser(user: UserUIModel){
        viewModelScope{
            when (val status = authorizeUserUseCase.authorizeUser(userUIDomainMapper(user))) {
                is Resource.Success -> {
                    saveUserSessionUseCase.saveUserSession(user.username)
                    setNavigation(QuizLogInFragmentDirections.actionQuizLogInFragmentToQuizHomeFragment())
                }
                is Resource.Error -> {
                    setErrorValue(status.message)
                }
            }
        }
    }
}
