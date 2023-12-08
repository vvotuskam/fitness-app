package com.fitness.app.trainservice.controller.request

data class TrainRequest(
    val name: String?,
    val description: String?,
    val date: String?,
    val time: String?,
    val instructorEmail: String,
    val instructorFullName: String
)