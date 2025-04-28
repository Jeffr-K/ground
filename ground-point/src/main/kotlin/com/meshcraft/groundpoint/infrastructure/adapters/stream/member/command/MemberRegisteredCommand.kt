package com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command

import com.meshcraft.groundcore.converter.Command
import com.meshcraft.groundpoint.core.values.PointActionType

data class MemberRegisteredCommand(
    val memberId: Long,
    val pointActionType: PointActionType,
    val referenceId: String
) : Command
