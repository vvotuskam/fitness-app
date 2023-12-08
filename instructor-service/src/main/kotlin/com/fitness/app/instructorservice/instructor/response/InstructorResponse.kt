package com.fitness.app.instructorservice.instructor.response

import com.fitness.app.instructorservice.instructor.Instructor

data class InstructorResponse(
    val id: Long,
    val fullName: String,
    val email: String
) {
    constructor(instructor: Instructor) :
            this(instructor.id!!, instructor.fullName, instructor.email)
}