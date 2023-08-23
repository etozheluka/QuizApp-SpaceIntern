package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.R
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserAuthRepository
import com.space.quizapp.utils.Resource
import com.space.quizapp.utils.UsernameValidator.isValidUsername

class AuthorizeUserUseCaseImpl(private val userAuthRepository: UserAuthRepository) : AuthorizeUserUseCase {

    override suspend fun authorizeUser(user: UserDomainModel):Resource {
        if (!isValidUsername(user.username)) {
            return Resource.Error(R.string.invalid_username)
        }

        val existingUser = userAuthRepository.getUserByUsername(user.username)
        if (existingUser != null) {
            return Resource.Success
        }

        userAuthRepository.saveUser(user)
        return Resource.Success
    }
}