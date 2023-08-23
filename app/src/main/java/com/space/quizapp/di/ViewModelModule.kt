package com.space.quizapp.di


import com.space.quizapp.presentation.model.mapper.quiz.QuizDomainUIMapper
import com.space.quizapp.presentation.model.mapper.user.UserUIDomainMapper
import com.space.quizapp.presentation.model.mapper.quiz.question.QuestionDomainUIMapper
import com.space.quizapp.presentation.model.mapper.quiz_point.QuizPointDomainUIMapper
import com.space.quizapp.presentation.ui.home.QuizHomeViewModel
import com.space.quizapp.presentation.ui.points.QuizPointsViewModel
import com.space.quizapp.presentation.ui.quiz.QuizViewModel
import com.space.quizapp.presentation.ui.log_in.QuizLogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        QuizLogInViewModel(get(), UserUIDomainMapper(),get(),get())
    }
    viewModel{
        QuizHomeViewModel(get(),get(),get(),get(), QuizDomainUIMapper())
    }
    viewModel{
        QuizPointsViewModel(get(),get(), QuizPointDomainUIMapper())
    }
    viewModel{
        QuizViewModel(
            get(),
            QuestionDomainUIMapper(),
            get(),
            get()
        )
    }
}