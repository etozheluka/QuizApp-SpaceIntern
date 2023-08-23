package com.space.quizapp.data.repository

import com.space.quizapp.data.local.dao.UserDao
import com.space.quizapp.data.local.model.mapper.user.UserDomainEntityMapper
import com.space.quizapp.data.local.model.mapper.user.UserEntityDomainMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserAuthRepository

class UserAuthRepositoryImpl(
    private val userDao: UserDao,
    private val domainEntityMapper: UserDomainEntityMapper,
    private val entityDomainMapper: UserEntityDomainMapper
) : UserAuthRepository {
    /**
     * This functions is used to handle room database operations
     */
    override suspend fun getUserByUsername(username: String) =
        userDao.getUserByUsername(username)?.let { entityDomainMapper(it) }

    override suspend fun saveUser(user: UserDomainModel) {
        userDao.saveUser(domainEntityMapper(user))
    }

    override suspend fun getUserPoints(userId: String): Double {
        return userDao.getQuizPointsForSubject(userId)
    }
}