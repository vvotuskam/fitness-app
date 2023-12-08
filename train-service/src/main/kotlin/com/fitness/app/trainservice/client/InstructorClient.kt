package com.fitness.app.trainservice.client

import com.fitness.app.trainservice.controller.request.InstructorRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    url = "http://localhost:8081/api/instructor",
    name = "instructor-client"
)
interface InstructorClient {

    @GetMapping
    fun getAll(@RequestHeader("Authorization") token: String): List<InstructorResponse>

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
        @RequestHeader("Authorization") token: String
    ): InstructorResponse?

    @PostMapping("/create")
    fun create(
        @RequestBody request: InstructorRequest,
        @RequestHeader("Authorization") token: String
    ): InstructorResponse
}