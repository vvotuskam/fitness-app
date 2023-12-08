package com.fitness.app.trainservice.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class KeycloakJwtConverter : Converter<Jwt, AbstractAuthenticationToken> {

    companion object {
        private const val ROLE_PREFIX = "ROLE_"
        private const val REALM_ACCESS = "realm_access"
        private const val ROLES = "roles"
        private const val EMAIL = "email"
    }

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val email = extractEmail(jwt)
        val authorities = extractAuthorities(jwt)
        val user = SecurityUser(email, authorities, jwt.tokenValue)
        return UsernamePasswordAuthenticationToken(user, "", authorities)
    }

    private fun extractEmail(jwt: Jwt): String {
        return jwt.claims[EMAIL] as String
    }

    private fun extractAuthorities(jwt: Jwt): List<GrantedAuthority> {
        val realmAccess = jwt.claims[REALM_ACCESS] as Map<*, *>
        val roles = realmAccess[ROLES] as List<*>
        return roles
            .map { role -> "$ROLE_PREFIX$role" }
            .map { role -> SimpleGrantedAuthority(role) }
            .toList()
    }
}