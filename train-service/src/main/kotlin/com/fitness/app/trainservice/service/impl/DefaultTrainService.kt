package com.fitness.app.trainservice.service.impl

import com.fitness.app.trainservice.controller.response.TrainResponse
import com.fitness.app.trainservice.repository.TrainParticipantsRepository
import com.fitness.app.trainservice.repository.TrainRepository
import com.fitness.app.trainservice.service.TrainService
import com.fitness.app.trainservice.util.DateFormatter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

@Service
class DefaultTrainService(
    private val trainRepository: TrainRepository,
    private val participantsRepository: TrainParticipantsRepository,
    private val dateFormatter: DateFormatter
) : TrainService {

    override fun getAll(): List<TrainResponse> {
        return trainRepository.findAll()
            .map {
                TrainResponse(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    date = dateFormatter.toDate(it.date),
                    time = dateFormatter.toTime(it.date)
                )
            }
    }

    override fun getById(id: Long): TrainResponse? {
        val train = trainRepository.findByIdOrNull(id) ?: return null
        return TrainResponse(
            id = train.id,
            name = train.name,
            description = train.description,
            date = dateFormatter.toDate(train.date),
            time = dateFormatter.toTime(train.date)
        )
    }

    @Transactional
    override fun deleteById(id: Long) {
        trainRepository.deleteById(id)
    }

}