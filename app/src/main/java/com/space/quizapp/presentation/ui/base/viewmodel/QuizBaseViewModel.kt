package com.space.quizapp.presentation.ui.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.space.quizapp.utils.extensions.SingleLiveEvent


abstract class QuizBaseViewModel:ViewModel() {
    private val _errorStatus = MutableLiveData<Any>()
    val errorStatus get() = _errorStatus

    //TODO ADD LOAD HANDLING
    private val _loadStatus = MutableLiveData<Int>()
    val loadStatus get() = _loadStatus

    private val _navigationState = SingleLiveEvent<NavDirections?>()
    val navigationState get() = _navigationState

    fun setErrorValue(message: Any) {
        _errorStatus.value = message
    }

    fun setLoadValue(message: Int) {
        _loadStatus.value = message
    }

    fun setNavigation(direction: NavDirections) {
        _navigationState.value = direction
    }

}