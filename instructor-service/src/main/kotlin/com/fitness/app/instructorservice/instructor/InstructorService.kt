package com.fitness.app.instructorservice.instructor

import com.fitness.app.instructorservice.instructor.request.InstructorRequest
import com.fitness.app.instructorservice.instructor.response.InstructorResponse

interface InstructorService {

    fun getAll(): List<InstructorResponse>

    fun getById(id: Long): InstructorResponse?

    fun create(request: InstructorRequest): InstructorResponse
}