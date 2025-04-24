package com.meshcraft.groundalarm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GroundAlarmApplication

fun main(args: Array<String>) {
    runApplication<GroundAlarmApplication>(*args)
}
