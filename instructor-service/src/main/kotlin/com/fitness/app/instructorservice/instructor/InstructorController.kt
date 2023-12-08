package com.fitness.app.instructorservice.instructor

import com.fitness.app.instructorservice.instructor.response.InstructorResponse
import com.fitness.app.instructorservice.instructor.request.InstructorRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.Socket

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/instructor")
class InstructorController(
    private val instructorService: InstructorService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<InstructorResponse>> {
        return ResponseEntity.ok(instructorService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<InstructorResponse?> {
        return ResponseEntity.ok(instructorService.getById(id))
    }

    @PostMapping("/create")
    fun create(
        @RequestBody request: InstructorRequest,
    ): ResponseEntity<InstructorResponse> {
        return ResponseEntity.ok(instructorService.create(request))
    }
}