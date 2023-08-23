package com.space.quizapp.di

import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoEntityMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDtoMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_point.QuizPointEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.user.UserDomainEntityMapper
import com.space.quizapp.data.local.model.mapper.user.UserEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDomainMapper
import com.space.quizapp.data.repository.QuizRepositoryImpl
import com.space.quizapp.data.repository.UserAuthRepositoryImpl
import com.space.quizapp.data.repository.UserQuizPointsRepositoryImpl
import com.space.quizapp.data.repository.UserSessionRepositoryImpl
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.domain.repository.UserAuthRepository
import com.space.quizapp.domain.repository.UserQuizPointsRepository
import com.space.quizapp.domain.repository.UserSessionRepository
import org.koin.dsl.module

val repositoryModule = module {

    //TODO DI FOR MAPPERS
    single<UserAuthRepository> {
        UserAuthRepositoryImpl(
            get(), UserDomainEntityMapper(),
            UserEntityDomainMapper()
        )
    }
    single<UserSessionRepository> { UserSessionRepositoryImpl(get()) }

    single<QuizRepository>{ QuizRepositoryImpl(
        get(),
        QuizDtoDomainMapper(),
        QuizDtoEntityMapper(),
        QuizEntityDomainMapper(),
        QuizPointEntityDomainMapper(),
        QuizQuestionEntityDomainMapper(),
        QuizQuestionEntityDtoMapper(),
        get()
    )}

    single<UserQuizPointsRepository> { UserQuizPointsRepositoryImpl(get()) }
}