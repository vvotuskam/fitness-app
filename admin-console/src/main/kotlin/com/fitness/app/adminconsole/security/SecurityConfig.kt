package com.fitness.app.adminconsole.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun filterChain(
        http: HttpSecurity,
        keycloakJwtConverter: KeycloakJwtConverter,
    ): SecurityFilterChain {
        http
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt -> jwt.jwtAuthenticationConverter(keycloakJwtConverter) }
            }
        return http.build()
    }
}