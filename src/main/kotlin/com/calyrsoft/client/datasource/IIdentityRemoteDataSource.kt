package com.calyrsoft.client.datasource

import com.calyrsoft.client.model.User

interface IIdentityRemoteDataSource {
    fun getByCi(ci: String): User
}