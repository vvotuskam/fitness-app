package com.fitness.app.trainservice.service

import com.fitness.app.trainservice.controller.response.TrainResponse

interface TrainService {

    fun getAll(): List<TrainResponse>

    fun getById(id: Long): TrainResponse?

    fun deleteById(id: Long)

}