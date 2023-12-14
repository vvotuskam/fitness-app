package com.fitness.app.adminconsole.keycloak

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("access_token")
    val accessToken: String
)