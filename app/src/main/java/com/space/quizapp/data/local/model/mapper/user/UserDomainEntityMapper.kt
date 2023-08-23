package com.space.quizapp.data.local.model.mapper.user


import com.space.quizapp.data.local.model.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserDomainEntityMapper() : ModelMapper<UserDomainModel, UserEntity> {
    override operator fun invoke(model: UserDomainModel): UserEntity =
        with(model){
            UserEntity(
                id = id,
                username = username,
            )
        }

}