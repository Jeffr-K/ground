package com.meshcraft.groundcore.mq

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class ComplexServiceIdentifierGenerator {
    companion object {
        fun generate(serviceName: String, entity: String, entityId: Long): String {
            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
            return "$serviceName:$entity:$entityId:$timestamp"
        }

        fun parse(identifier: String): Map<String, String> {
            return identifier
                .split(":")
                .takeIf { it.size == 4 }
                ?.let { parts -> mapOf(
                        "serviceName" to parts[0],
                        "entityType" to parts[1],
                        "entityId" to parts[2],
                        "timestamp" to parts[3]
                    )
                }
                ?: throw IllegalArgumentException("Invalid complex identifier format $identifier")
        }
    }
}