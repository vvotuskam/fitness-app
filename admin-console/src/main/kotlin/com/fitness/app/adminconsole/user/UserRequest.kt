package com.fitness.app.adminconsole.user

data class UserRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)