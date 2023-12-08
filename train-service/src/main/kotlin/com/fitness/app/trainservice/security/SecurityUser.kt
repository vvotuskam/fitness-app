package com.fitness.app.trainservice.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    username: String,
    authorities: Collection<GrantedAuthority>,
    val jwt: String
): User(username, "", authorities)