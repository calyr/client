package com.calyrsoft.client.controller

import com.calyrsoft.client.exception.UserNotFoundException
import com.calyrsoft.client.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController: ClientApi {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/user/{id}")
    override fun getUser(@PathVariable id: String): Any {
        try {
            val user = userService.getUser(id)
            return ResponseEntity.ok(user)
        } catch (e: UserNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.body)
        } catch (e: Exception) {
            return ResponseEntity.internalServerError()
        }

    }



}