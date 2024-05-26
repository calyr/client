package com.calyrsoft.client.repository

import com.calyrsoft.client.datasource.IIdentityRemoteDataSource
import com.calyrsoft.client.model.User
import org.springframework.stereotype.Component

@Component
class IdentityRepository(val dataSource: IIdentityRemoteDataSource): IIdentityRepository {
    override fun findByCi(ci: String): User {
        return dataSource.getByCi(ci)
    }

}