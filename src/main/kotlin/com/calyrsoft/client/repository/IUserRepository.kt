package com.calyrsoft.client.repository

import com.calyrsoft.client.model.User

interface IUserRepository {
    fun findById(id: String): User?
    fun delete(id: String): Boolean
    fun create(user: User): User
    fun findAll(): List<User>
}