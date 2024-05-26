package com.calyrsoft.client.service

import com.calyrsoft.client.exception.IdentityConflictException
import com.calyrsoft.client.exception.UserNotFoundException
import com.calyrsoft.client.model.User
import com.calyrsoft.client.repository.IIdentityRepository
import com.calyrsoft.client.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService(
    @Autowired private val userRepository: IUserRepository,
    @Autowired private val identityRepository: IIdentityRepository
) {

    fun getUser(id: String): User {
        val userResult = userRepository.findById(id)
            ?: throw UserNotFoundException(
                message = "User not found",
                detail = "The userId was not found in the localCache.",
                errors = emptyList()
            )
        return userResult
    }

    fun create(user: User): User {

        val userIdentity = identityRepository.findByCi(user.id)

        val resultValidate = validateIdentity(user, userIdentity)
        if( resultValidate) {
            return userRepository.create(user)
        } else {
            throw IdentityConflictException(
               message = "Could not create user because the result identity is different to the source",
                detail = "Could not create user because the result identity is different to the source")
        }
    }

    private fun validateIdentity(userNew: User, userIdentity: User) : Boolean {
        return userNew.name == userIdentity.name
    }
}