package com.fitness.app.adminconsole.user

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    fun getAllUsers() : ResponseEntity<*> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @GetMapping("/{userId}/roles")
    fun getUserRoles(@PathVariable userId: String): ResponseEntity<*> {
        return ResponseEntity.ok(userService.getUserRoles(userId))
    }

    @PostMapping
    fun createUser(
        @RequestParam(name = "role", defaultValue = "USER") role: String,
        @RequestBody request: UserRequest
    ): ResponseEntity<*> {
        return ResponseEntity.ok(userService.createUser(
            request = request,
            roles = arrayOf(role)
        ))
    }

    @DeleteMapping("/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
    fun deleteByEmail(@PathVariable userId: String): ResponseEntity<*> {
        return ResponseEntity.ok(userService.deleteUser(userId))
    }
}