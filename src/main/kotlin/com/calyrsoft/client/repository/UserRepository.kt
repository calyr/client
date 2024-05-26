package com.calyrsoft.client.repository

import com.calyrsoft.client.model.User
import org.springframework.stereotype.Component

@Component
class UserRepository: IUserRepository {

    val listOfUsers = mutableListOf(
        User(
            "1", "Roberto Carlos", "Callisaya"
        ),
        User(
            "2", "Gabriela", "Orosco"
        ),
        User(
            "3", "Stephanie", "Lopez"
        ),
        User(
            "4", "Nicole", "Peña"
        ),
        User(
            "5", "Elias", "Ovando"
        ),
        User(
            "6", "Escolastica", "Flores"
        ),
        User(
            "7", "Gonzalo", "Montaño"
        )
    )

    override fun findById(id: String): User? {
       return listOfUsers.find { user -> user.id == id }
    }

    override fun delete(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun create(user: User): User {
        listOfUsers.add(user)
        return user
    }

    override fun findAll(): List<User> {
        TODO("Not yet implemented")
    }
}