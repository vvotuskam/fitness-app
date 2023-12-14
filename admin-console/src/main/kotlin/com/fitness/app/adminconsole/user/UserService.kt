package com.fitness.app.adminconsole.user

import com.fitness.app.adminconsole.keycloak.KeycloakRole
import java.util.UUID

interface UserService {

    fun getAllUsers(): List<User>

    fun getUserRoles(userId: String): List<KeycloakRole>

    fun createUser(request: UserRequest, vararg roles: String)

    fun deleteUser(userId: String)
}