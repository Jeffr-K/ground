package com.meshcraft.groundmember.infrastructure.kafka

import com.meshcraft.groundcore.mq.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun send(topic: String, message: Message<Any>) {
        val future: CompletableFuture<SendResult<String, Any>> = kafkaTemplate.send(topic, message.id, message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                println("Message sent: ${result.recordMetadata}")
            } else {
                println("Failed to send message: ${ex.message}")
            }
        }
    }
}