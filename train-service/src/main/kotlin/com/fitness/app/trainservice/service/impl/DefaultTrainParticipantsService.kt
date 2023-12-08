package com.fitness.app.trainservice.service.impl

import com.fitness.app.trainservice.entity.TrainParticipants
import com.fitness.app.trainservice.repository.TrainParticipantsRepository
import com.fitness.app.trainservice.repository.TrainRepository
import com.fitness.app.trainservice.security.SecurityUser
import com.fitness.app.trainservice.service.TrainParticipantsService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultTrainParticipantsService(
    private val trainRepository: TrainRepository,
    private val trainParticipantsRepository: TrainParticipantsRepository
) : TrainParticipantsService {

    @Transactional
    override fun apply(trainId: Long) {
        val train = trainRepository.findByIdOrNull(trainId) ?: return

        val applied = train.participants.firstOrNull { it.email == getUser().username } != null
        if (applied) return

        val trainParticipants = TrainParticipants(
            train = train,
            email = getUser().username
        )

        trainParticipantsRepository.save(trainParticipants)
    }

    @Transactional
    override fun cancel(trainId: Long) {
        val train = trainRepository.findByIdOrNull(trainId) ?: return

        val cancelled = train.participants.firstOrNull { it.email == getUser().username } == null
        if (cancelled) return

        trainParticipantsRepository.deleteByEmailAndTrain(
            email = getUser().username,
            train = train
        )
    }

    private fun getUser(): SecurityUser {
        return SecurityContextHolder.getContext().authentication.principal as SecurityUser
    }
}