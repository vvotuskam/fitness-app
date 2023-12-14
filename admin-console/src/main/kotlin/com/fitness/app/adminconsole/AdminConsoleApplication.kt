package com.fitness.app.adminconsole

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AdminConsoleApplication

fun main(args: Array<String>) {
    runApplication<AdminConsoleApplication>(*args)
}
