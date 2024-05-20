package com.calyrsoft.client.service

import com.calyrsoft.client.exception.UserNotFoundException
import com.calyrsoft.client.model.User
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
}