package com.fitness.app.trainservice.repository

import com.fitness.app.trainservice.entity.Train
import com.fitness.app.trainservice.entity.TrainParticipants
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainParticipantsRepository : JpaRepository<TrainParticipants, Long> {

    fun deleteByEmailAndTrain(email: String, train: Train)

    fun existsByEmailAndTrain(email: String, train: Train): Boolean
}