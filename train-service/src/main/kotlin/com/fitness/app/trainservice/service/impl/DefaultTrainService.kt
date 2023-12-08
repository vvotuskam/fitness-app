package com.fitness.app.trainservice.service.impl

import com.fitness.app.trainservice.client.InstructorClient
import com.fitness.app.trainservice.client.InstructorResponse
import com.fitness.app.trainservice.controller.request.InstructorRequest
import com.fitness.app.trainservice.controller.request.TrainRequest
import com.fitness.app.trainservice.controller.response.TrainResponse
import com.fitness.app.trainservice.entity.Train
import com.fitness.app.trainservice.repository.TrainParticipantsRepository
import com.fitness.app.trainservice.repository.TrainRepository
import com.fitness.app.trainservice.security.SecurityUser
import com.fitness.app.trainservice.service.TrainService
import com.fitness.app.trainservice.util.DateFormatter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class DefaultTrainService(
    private val trainRepository: TrainRepository,
    private val participantsRepository: TrainParticipantsRepository,
    private val dateFormatter: DateFormatter,
    private val instructorClient: InstructorClient
) : TrainService {

    override fun getAll(): List<TrainResponse> {
        return trainRepository.findAll()
            .map {
                TrainResponse(
                    id = it.id!!,
                    name = it.name,
                    description = it.description,
                    date = dateFormatter.toDate(it.date),
                    time = dateFormatter.toTime(it.date),
                    instructorName = getInstructorName(it.instructorId),
                    applied = isApplied(it)
                )
            }
    }

    override fun getAllApplied(): List<TrainResponse> {
        return getAll().filter { it.applied }
//        return trainRepository.findAllApplied(getUser().username)
//            .map {
//                TrainResponse(
//                    id = it.id!!,
//                    name = it.name,
//                    description = it.description,
//                    date = dateFormatter.toDate(it.date),
//                    time = dateFormatter.toTime(it.date),
//                    instructorName = getInstructorName(it.instructorId),
//                    applied = isApplied(it)
//                )
//            }
    }

    override fun getById(id: Long): TrainResponse? {
        val train = trainRepository.findByIdOrNull(id) ?: return null
        return TrainResponse(
            id = train.id!!,
            name = train.name,
            description = train.description,
            date = dateFormatter.toDate(train.date),
            time = dateFormatter.toTime(train.date),
            instructorName = getInstructorName(train.instructorId),
            applied = isApplied(train)
        )
    }

    @Transactional
    override fun deleteById(id: Long) {
        trainRepository.deleteById(id)
    }

    override fun create(request: TrainRequest) {

        val instructorRequest = InstructorRequest(
            fullName = request.instructorFullName,
            email = request.instructorEmail
        )
        val instructorResponse = instructorClient.create(
            request = instructorRequest,
            token = "Bearer ${getUser().jwt}"
        )

        val train = Train(
            name = request.name ?: "",
            description = request.description ?: "",
            date = toLocalDateTime(request.date!!, request.time!!),
            instructorId = instructorResponse.id,
            participants = emptyList()
        )

        trainRepository.save(train)
    }

    private fun getInstructorName(id: Long): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = auth.principal as SecurityUser
        return instructorClient.getById(id, "Bearer ${user.jwt}")?.fullName ?: "No instructor"
    }

    private fun getUser(): SecurityUser {
        val auth = SecurityContextHolder.getContext().authentication
        return auth.principal as SecurityUser
    }

    private fun toLocalDateTime(date: String, time: String): LocalDateTime {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val localDate = LocalDate.parse(date, dateFormatter)
        val localTime = LocalTime.parse(time, timeFormatter)

        return LocalDateTime.of(localDate, localTime)
    }

    private fun isApplied(train: Train): Boolean {
        return participantsRepository.existsByEmailAndTrain(getUser().username, train)
    }
}