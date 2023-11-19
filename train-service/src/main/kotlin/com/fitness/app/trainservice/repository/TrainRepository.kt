package com.fitness.app.trainservice.repository

import com.fitness.app.trainservice.entity.Train
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainRepository : JpaRepository<Train, Long> {
}