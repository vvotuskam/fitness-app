package com.fitness.app.trainservice.controller.response

import java.time.LocalDateTime

data class TrainResponse(
    val id: Long,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val instructorName: String,
    val applied: Boolean
)