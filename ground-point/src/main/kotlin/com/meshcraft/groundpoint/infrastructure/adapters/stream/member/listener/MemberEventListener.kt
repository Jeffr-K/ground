package com.meshcraft.groundpoint.infrastructure.adapters.stream.member.listener

import com.meshcraft.groundcore.mq.Message
import com.meshcraft.groundcore.mq.contracts.KafkaTopicConstants
import com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command.MemberRegisteredCommand
import com.meshcraft.groundpoint.core.command.PointCommandHandler
import com.meshcraft.groundpoint.core.values.PointActionType
import com.meshcraft.groundpoint.core.query.PointQueryHandler
import com.meshcraft.groundpoint.infrastructure.converter.MemberRegisteredCommandConverter
import com.meshcraft.groundpoint.infrastructure.converter.MemberWithdrawalCommandConverter
import com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command.MemberRegisteredEvent
import com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command.MemberWithdrawalCommand
import com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command.MemberWithdrawalEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MemberEventListener(
    private val pointCommandHandler: PointCommandHandler,
    private val pointQueryHandler: PointQueryHandler,
    private val memberRegisteredCommandConverter: MemberRegisteredCommandConverter,
    private val memberWithdrawalCommandConverter: MemberWithdrawalCommandConverter
) {

    @KafkaListener(topics = [KafkaTopicConstants.Member.Downstream.REGISTERED], groupId = "members")
    fun userRegisteredEventHandler(event: Message<MemberRegisteredEvent>) {
        this.pointCommandHandler.save(port =
            this.memberRegisteredCommandConverter.convert(
                MemberRegisteredCommand(
                    memberId = event.content.memberId,
                    pointActionType = PointActionType.REGISTERED,
                    referenceId = event.id
                )
            )
        )
    }

    @KafkaListener(topics = [KafkaTopicConstants.Member.Downstream.WITHDRAWAL], groupId = "members")
    fun userWithdrawalEventHandler(event: MemberWithdrawalEvent) {
        val point = this.pointQueryHandler.getPointBy(memberId = event.memberId)
            ?: throw IllegalArgumentException("the point could freeze is not exist")
        this.pointCommandHandler.freeze(port =
            this.memberWithdrawalCommandConverter.convert(
                MemberWithdrawalCommand(
                    point = point,
                    memberId = event.memberId
                )
            )
        )
    }
}