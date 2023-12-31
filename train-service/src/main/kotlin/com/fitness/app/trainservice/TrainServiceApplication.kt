package com.fitness.app.trainservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
class TrainServiceApplication

fun main(args: Array<String>) {
	runApplication<TrainServiceApplication>(*args)
}
