package com.meshcraft.groundmember

import com.meshcraft.groundcore.annotations.EnableGroundCoreModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableGroundCoreModule
class GroundMemberApplication

fun main(args: Array<String>) {
    runApplication<GroundMemberApplication>(*args)
}
