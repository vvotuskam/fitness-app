package com.fitness.app.trainservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "trains")
data class Train(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,

    val description: String,

    val date: LocalDateTime,

    val instructorId: Long,

    @OneToMany(mappedBy = "train")
    val participants: List<TrainParticipants>
)