package com.fitness.app.adminconsole.keycloak

import java.util.*

data class KeycloakRole(
    val id: String,
    val name: String,
) {
    companion object {
        val ROLES = arrayOf(
            KeycloakRole(
                "4a8c1d56-d019-4ae7-ab28-36f2bfc164bf",
                "USER"
            ),
            KeycloakRole(
                "073aa26d-b77b-41b7-a586-1363361657fd",
                "ADMIN"
            ),
            KeycloakRole(
                "1fa0bc38-424c-4478-9aef-39e5bb8f07df",
                "INSTRUCTOR"
            )
        )
    }
}