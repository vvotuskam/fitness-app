package com.fitness.app.trainservice.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "train_participants")
data class TrainParticipants(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "train_id")
    val train: Train,

    val email: String
)