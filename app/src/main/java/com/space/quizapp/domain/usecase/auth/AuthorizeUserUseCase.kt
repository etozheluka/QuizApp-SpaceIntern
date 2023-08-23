package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.Resource

interface AuthorizeUserUseCase {
    suspend fun authorizeUser(user: UserDomainModel) : Resource
}