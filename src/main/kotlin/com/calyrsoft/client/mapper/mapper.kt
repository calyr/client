package com.calyrsoft.client.mapper

import com.calyrsoft.client.datasource.UserIdentityModel
import com.calyrsoft.client.model.User

fun UserIdentityModel.toUserModel() : User {
    return User(
        id = ci,
        name = name,
        lastName = firstLastName
    )
}