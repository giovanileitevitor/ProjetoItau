package com.nm.data.mapper

import com.nm.data.entity.User
import com.nm.data.model.UserSchema
import com.nm.infra.net.data.Mapper

class UserSchemaToUser : Mapper<UserSchema, User>() {

    override fun transform(item: UserSchema): User {
        return User(
            login = item.login,
            avatar_url = item.avatar_url,
            type = item.type
        )
    }
}