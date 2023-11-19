package com.fitness.app.trainservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "train_participants")
data class TrainParticipants(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "train_id")
    val train: Train,

    val participantId: Long
)