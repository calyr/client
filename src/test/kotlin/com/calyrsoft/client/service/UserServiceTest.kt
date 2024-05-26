package com.calyrsoft.client.service

import com.calyrsoft.client.exception.IdentityConflictException
import com.calyrsoft.client.exception.UserNotFoundException
import com.calyrsoft.client.model.User
import com.calyrsoft.client.repository.IIdentityRepository
import com.calyrsoft.client.repository.IUserRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    lateinit var userRepository: IUserRepository

    @Mock
    lateinit var identityRepository: IIdentityRepository

    @InjectMocks
    lateinit var userService: UserService

    @Test
    @DisplayName("Validate is the user exits in the respository")
    fun getUser() {
        val userExpected = User("1", "Roberto", "Callisaya")
        Mockito.`when`(userRepository.findById("1")).thenReturn(userExpected)
        val userResult = userService.getUser("1")
        assert(userResult == userExpected)
    }

    @Test
    @DisplayName("When the user not fouend would return a UserNotFouendException")
    fun userNotFound() {
        Mockito.`when`(userRepository.findById("1")).thenReturn(null)
        assertThrows<UserNotFoundException> {
            val result = userService.getUser("1")
        }
    }

    @Test
    @DisplayName("When the create user was successful")
    fun createUser() {
        val newUser = User(id = "66078888", name = "Adhemar", lastName = "Callisaya")

        Mockito.`when`(userRepository.create(newUser)).thenReturn(newUser)
        Mockito.`when`(identityRepository.findByCi(newUser.id)).thenReturn(newUser)
        val userCreated = userService.create(newUser)
        assert( newUser.id == userCreated.id)
    }

    @Test
    @DisplayName("When the ci has more one owner would return an Exception")
    fun exceptionWhenUserHasDifferentOwner() {
        val newUser = User(id = "66078888", name = "Adhemar", lastName = "Callisaya")

        Mockito.`when`(identityRepository.findByCi(newUser.id)).thenThrow(IdentityConflictException::class.java)

        assertThrows<IdentityConflictException> {
            val userCreated = userService.create(newUser)
        }
    }
}