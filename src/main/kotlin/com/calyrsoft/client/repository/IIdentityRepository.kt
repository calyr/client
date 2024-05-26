package com.calyrsoft.client.repository

import com.calyrsoft.client.exception.IdentityConflictException
import com.calyrsoft.client.model.User
import kotlin.jvm.Throws

interface IIdentityRepository {
    @Throws(IdentityConflictException::class)
    fun findByCi(ci: String): User
}