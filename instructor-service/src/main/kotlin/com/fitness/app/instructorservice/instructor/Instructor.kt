package com.fitness.app.instructorservice.instructor;

import jakarta.persistence.*

@Entity
@Table(name = "instructors")
data class Instructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val fullName: String,

    val email: String
)