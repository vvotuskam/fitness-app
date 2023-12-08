package com.fitness.app.instructorservice.instructor

import com.fitness.app.instructorservice.instructor.request.InstructorRequest
import com.fitness.app.instructorservice.instructor.response.InstructorResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultInstructorService(
    private val instructorRepository: InstructorRepository
) : InstructorService {

    override fun getAll(): List<InstructorResponse> {
        return instructorRepository.findAll().map { InstructorResponse(it) }
    }

    override fun getById(id: Long): InstructorResponse? {
        val instructor = instructorRepository.findByIdOrNull(id) ?: return null
        return InstructorResponse(instructor)
    }

    @Transactional
    override fun create(request: InstructorRequest): InstructorResponse {
        val instructor = instructorRepository.findByEmail(request.email)
        if (instructor != null) return InstructorResponse(instructor)

        val saved = instructorRepository.save(
            Instructor(
                fullName = request.fullName,
                email = request.email
            )
        )
        return InstructorResponse(saved)
    }
}