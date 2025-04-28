package com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command

import com.meshcraft.groundcore.converter.Command
import com.meshcraft.groundpoint.core.entity.Point

data class MemberWithdrawalCommand(val point: Point, val memberId: Long) : Command