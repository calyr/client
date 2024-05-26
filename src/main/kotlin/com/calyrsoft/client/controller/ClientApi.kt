package com.calyrsoft.client.controller

import com.calyrsoft.client.model.User

interface ClientApi {

    fun getUser(id: String): Any
    fun create(user: User) : Any
}