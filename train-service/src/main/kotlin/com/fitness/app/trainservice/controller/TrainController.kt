package com.fitness.app.trainservice.controller

import com.fitness.app.trainservice.controller.request.TrainRequest
import com.fitness.app.trainservice.controller.response.TrainResponse
import com.fitness.app.trainservice.service.TrainParticipantsService
import com.fitness.app.trainservice.service.TrainService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/train")
class TrainController(
    private val trainService: TrainService,
    private val trainParticipantsService: TrainParticipantsService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<TrainResponse>> {
        return ResponseEntity.ok(trainService.getAll())
    }

    @GetMapping("/applied")
    fun getAllApplied(): ResponseEntity<List<TrainResponse>> {
        return ResponseEntity.ok(trainService.getAllApplied())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TrainResponse?> {
        return ResponseEntity.ok(trainService.getById(id))
    }

    @PostMapping("/create")
    fun create(@RequestBody request: TrainRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok(trainService.create(request))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(trainService.deleteById(id))
    }

    @PatchMapping("/apply/{id}")
    fun apply(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(trainParticipantsService.apply(id))
    }

    @PatchMapping("/cancel/{id}")
    fun cancel(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(trainParticipantsService.cancel(id))
    }
}