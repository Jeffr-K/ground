package com.meshcraft.groundmember.core.command

import com.meshcraft.groundcore.mq.ComplexServiceIdentifierGenerator
import com.meshcraft.groundcore.mq.Message
import com.meshcraft.groundcore.mq.contracts.KafkaTopicConstants
import com.meshcraft.groundmember.core.entity.Member
import com.meshcraft.groundmember.core.event.MemberRegisteredEvent
import com.meshcraft.groundmember.core.repository.MemberRepository
import com.meshcraft.groundmember.infrastructure.kafka.KafkaProducer
import org.springframework.stereotype.Service

@Service
class MemberRegisterCommandHandler(
    private val kafkaProducer: KafkaProducer,
    private val memberRepository: MemberRepository
) {
    fun register(port: MemberRegisterCommand): Long {
        val newMember = Member.create(
            username = port.username,
            password = port.password,
            email = port.email,
            phone = port.phone
        )

        val registeredMember = this.memberRepository.save(newMember)

        this.kafkaProducer.send(
            topic = KafkaTopicConstants.Member.Downstream.REGISTERED,
            Message(
                id = ComplexServiceIdentifierGenerator.generate(
                    serviceName = "member-service",
                    entity = "member",
                    entityId = registeredMember.id!!
                ),
                content = MemberRegisteredEvent(memberId = registeredMember.id!!))
        )


        return registeredMember.id!!
    }
}