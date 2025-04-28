package com.meshcraft.groundprofile.infrastructure.converter

import com.meshcraft.groundcore.converter.GenericServicePortConverter
import com.meshcraft.groundprofile.core.command.ProfileDeletePort
import com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command.MemberWithdrawalCommand
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class MemberWithdrawalCommandConverter : GenericServicePortConverter<MemberWithdrawalCommand, ProfileDeletePort>()