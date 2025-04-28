package com.meshcraft.groundpoint.infrastructure.converter

import com.meshcraft.groundcore.converter.GenericServicePortConverter
import com.meshcraft.groundpoint.core.command.PointFreezePort
import com.meshcraft.groundpoint.infrastructure.adapters.stream.member.command.MemberWithdrawalCommand
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class MemberWithdrawalCommandConverter : GenericServicePortConverter<MemberWithdrawalCommand, PointFreezePort>()