package com.space.quizapp.di

import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCase
import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCaseImpl
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCaseImpl
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCaseImpl
import com.space.quizapp.domain.usecase.current_user.save.SaveUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.save.SaveUserSessionUseCaseImpl
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCase
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    //Room use cases
    single<AuthorizeUserUseCase> { AuthorizeUserUseCaseImpl(get()) }
    single<GetUserPointsUseCase> { GetUserPointsUseCaseImpl(get()) }
    //Data store use cases
    single<SaveUserSessionUseCase> { SaveUserSessionUseCaseImpl(get()) }
    single<GetUserSessionUseCase> { GetUserSessionUseCaseImpl(get()) }
    single<ClearUserSessionUseCase> { ClearUserSessionUseCaseImpl(get()) }
}