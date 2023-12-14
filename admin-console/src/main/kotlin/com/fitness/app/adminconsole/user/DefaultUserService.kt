package com.fitness.app.adminconsole.user

import com.fitness.app.adminconsole.keycloak.CreateUserRequest
import com.fitness.app.adminconsole.keycloak.KeycloakClient
import com.fitness.app.adminconsole.keycloak.KeycloakRole
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DefaultUserService(
    private val keycloak: KeycloakClient
) : UserService {

    private fun getBearerToken(): String {
        val tokenResponse = keycloak.getAdminToken()
        return "Bearer ${tokenResponse.body!!.accessToken}"
    }

    override fun getAllUsers(): List<User> {
        val usersResponse = keycloak.getAllUsers(getBearerToken())
        return usersResponse.body!!
    }

    override fun getUserRoles(userId: String): List<KeycloakRole> {
        val rolesResponse = keycloak.getUserRoles(userId, getBearerToken())
        return rolesResponse.body!!.realmMappings
    }

    override fun createUser(request: UserRequest, vararg roles: String) {
        val bearerToken = getBearerToken()
        val createUserRequest = CreateUserRequest(request)
        keycloak.createUser(bearerToken, createUserRequest)

        val userResponse = keycloak.getAllUsers(bearerToken, request.email)
        val user = userResponse.body!![0]
        val keycloakRoles = roles.mapNotNull { role ->
            KeycloakRole.ROLES.firstOrNull { role == it.name }
        }
        keycloak.assignRole(user.id, keycloakRoles, bearerToken)
    }

    override fun deleteUser(userId: String) {
        keycloak.deleteUser(userId, getBearerToken())
    }
}