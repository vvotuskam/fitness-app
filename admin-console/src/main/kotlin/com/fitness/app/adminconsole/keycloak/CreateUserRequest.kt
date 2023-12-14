package com.fitness.app.adminconsole.keycloak

import com.fitness.app.adminconsole.user.UserRequest

data class CreateUserRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val username: String = email,
    val enabled: Boolean = true,
    val emailVerified: Boolean = true,
    val credentials: Array<Credentials>,
) {

    data class Credentials(
        val type: String = "password",
        val temporary: Boolean = false,
        val value: String,
    )

    constructor(
        request: UserRequest,
    ) : this(
        email = request.email,
        firstName = request.firstName,
        lastName = request.lastName,
        credentials = arrayOf(
            Credentials(
                value = request.password
            )
        ),
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreateUserRequest

        if (email != other.email) return false
        if (username != other.username) return false
        if (enabled != other.enabled) return false
        if (emailVerified != other.emailVerified) return false
        if (!credentials.contentEquals(other.credentials)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + emailVerified.hashCode()
        result = 31 * result + credentials.contentHashCode()
        return result
    }
}