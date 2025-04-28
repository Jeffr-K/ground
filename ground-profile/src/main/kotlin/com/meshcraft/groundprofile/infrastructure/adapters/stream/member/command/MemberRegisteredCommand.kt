package com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command

import com.meshcraft.groundcore.converter.Command

data class MemberRegisteredCommand(
    val memberId: Long,
    val gender: String,
    val referenceId: String
) : Command
