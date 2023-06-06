package com.space.quizapp.data.local.entity.mapper.user

import com.space.quizapp.data.local.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserEntityDomainMapper : ModelMapper<UserEntity, UserDomainModel> {
    override operator fun invoke(model: UserEntity): UserDomainModel =
        UserDomainModel(
            id = model.id,
            username = model.username,
            points = model.points
        )
}