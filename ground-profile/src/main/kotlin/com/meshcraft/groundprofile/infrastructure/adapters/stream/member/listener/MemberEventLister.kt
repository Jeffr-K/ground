package com.meshcraft.groundprofile.infrastructure.adapters.stream.member.listener

import com.meshcraft.groundcore.mq.Message
import com.meshcraft.groundcore.mq.contracts.KafkaTopicConstants
import com.meshcraft.groundprofile.core.command.ProfileCommandHandler
import com.meshcraft.groundprofile.core.query.ProfileQueryHandler
import com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command.MemberRegisteredCommand
import com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command.MemberRegisteredEvent
import com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command.MemberWithdrawalCommand
import com.meshcraft.groundprofile.infrastructure.converter.MemberRegisteredCommandConverter
import com.meshcraft.groundprofile.infrastructure.converter.MemberWithdrawalCommandConverter
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MemberEventLister(
    private val profileCommandHandler: ProfileCommandHandler,
    private val profileQueryHandler: ProfileQueryHandler,
    private val memberRegisteredCommandConverter: MemberRegisteredCommandConverter,
    private val memberWithdrawalCommandConverter: MemberWithdrawalCommandConverter
) {
    @KafkaListener(topics = [KafkaTopicConstants.Member.Downstream.REGISTERED], groupId = "members")
    fun userRegisteredEventHandler(event: Message<MemberRegisteredEvent>) {
        profileCommandHandler.createProfile(port =
            memberRegisteredCommandConverter.convert(
                MemberRegisteredCommand(
                    memberId = event.content.memberId,
                    gender = event.content.gender,
                    referenceId = event.id
                )
            )
        )
    }

    @KafkaListener(topics = [KafkaTopicConstants.Member.Downstream.WITHDRAWAL], groupId = "members")
    fun userWithdrawalEventHandler(event: Message<MemberRegisteredEvent>) {
        val profile = profileQueryHandler.getProfileBy(memberId = event.content.memberId)
            ?: throw IllegalArgumentException("the profile could freeze is not exist")

        profileCommandHandler.deleteProfile(port =
            memberWithdrawalCommandConverter.convert(
                MemberWithdrawalCommand(
                    memberId = event.content.memberId,
                    profile = profile,
                )
            )
        )
    }
}