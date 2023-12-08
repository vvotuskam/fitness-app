package com.fitness.app.trainservice.service

interface TrainParticipantsService {

    fun apply(trainId: Long)

    fun cancel(trainId: Long)
}