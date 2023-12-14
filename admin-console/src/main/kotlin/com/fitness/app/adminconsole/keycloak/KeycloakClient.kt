package com.fitness.app.adminconsole.keycloak

import com.fitness.app.adminconsole.user.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@FeignClient(
    name = "keycloak-client",
    url = "http://localhost:8762"
)
interface KeycloakClient {

    companion object {
        private const val REALM = "fitness-app"
        private val TOKEN_BODY = mapOf(
            "password" to "admin",
            "username" to "admin",
            "grant_type" to "password",
            "client_id" to "admin-cli",
            "client_secret" to "nl9zfKXUpVx8tZYjIA52wHDVRhzYHLpq"
        )
    }

    @PostMapping(
        path = ["/realms/master/protocol/openid-connect/token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun getAdminToken(
        @RequestBody body: Map<String, Any> = TOKEN_BODY
    ): ResponseEntity<TokenResponse>

    @GetMapping("/admin/realms/${REALM}/users")
    fun getAllUsers(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestParam(name = "email", required = false) email: String = ""
    ): ResponseEntity<List<User>>

    @GetMapping("/admin/realms/${REALM}/users/{userId}/role-mappings")
    fun getUserRoles(
        @PathVariable userId: String,
        @RequestHeader("Authorization") bearerToken: String
    ): ResponseEntity<RoleMappings>

    @PostMapping(
        path = ["/admin/realms/${REALM}/users"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun createUser(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestBody request: CreateUserRequest
    ): ResponseEntity<Unit>

    @PostMapping(
        path = ["/admin/realms/${REALM}/users/{userId}/role-mappings/realm"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun assignRole(
        @PathVariable userId: String,
        @RequestBody roles: List<KeycloakRole>,
        @RequestHeader("Authorization") bearerToken: String
    )

    @DeleteMapping("/admin/realms/${REALM}/users/{userId}")
    fun deleteUser(
        @PathVariable userId: String,
        @RequestHeader("Authorization") bearerToken: String
    )
}