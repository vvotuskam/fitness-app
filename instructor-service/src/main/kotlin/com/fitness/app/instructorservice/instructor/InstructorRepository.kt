package com.fitness.app.instructorservice.instructor

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : JpaRepository<Instructor, Long> {

    fun findByEmail(email: String): Instructor?
}