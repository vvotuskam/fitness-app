package com.fitness.app.trainservice.repository

import com.fitness.app.trainservice.entity.Train
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TrainRepository : JpaRepository<Train, Long> {

    @Query("""
        select t from Train t, TrainParticipants p 
        where t.id = p.id and p.email = :email
    """)
    fun findAllApplied(email: String): List<Train>
}