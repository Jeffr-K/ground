package com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command

import com.meshcraft.groundcore.converter.Command
import com.meshcraft.groundprofile.core.entity.Profile

data class MemberWithdrawalCommand(val memberId: Long, profile: Profile) : Command
