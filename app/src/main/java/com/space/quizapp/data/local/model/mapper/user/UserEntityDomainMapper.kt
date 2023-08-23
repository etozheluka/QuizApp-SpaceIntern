package com.space.quizapp.data.local.model.mapper.user

import com.space.quizapp.data.local.model.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserEntityDomainMapper : ModelMapper<UserEntity, UserDomainModel> {
    override operator fun invoke(model: UserEntity): UserDomainModel =
        with(model) {
            UserDomainModel(
                id = id,
                username = username,
            )
        }
}