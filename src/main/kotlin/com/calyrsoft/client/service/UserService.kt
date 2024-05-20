package com.calyrsoft.client.service

import com.calyrsoft.client.exception.UserNotFoundException
import com.calyrsoft.client.model.User
import com.calyrsoft.client.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserService(@Autowired private val userRepository: IUserRepository) {

    fun getUser(id: String): User {
        val userResult = userRepository.findById(id)
            ?: throw UserNotFoundException(
                message = "User not found",
                detail = "The userId was not found in the localCache.",
                errors = emptyList()
            )
        return userResult
    }
}