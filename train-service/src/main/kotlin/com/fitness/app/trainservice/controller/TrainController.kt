package com.fitness.app.trainservice.controller

import com.fitness.app.trainservice.controller.response.TrainResponse
import com.fitness.app.trainservice.service.TrainService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/train")
class TrainController(
    private val trainService: TrainService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<TrainResponse>> {
        return ResponseEntity.ok(trainService.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TrainResponse?> {
        return ResponseEntity.ok(trainService.getById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(trainService.deleteById(id))
    }
}