package com.meshcraft.groundprofile.infrastructure.converter

import com.meshcraft.groundcore.converter.GenericServicePortConverter
import com.meshcraft.groundprofile.core.command.ProfileCreatePort
import com.meshcraft.groundprofile.infrastructure.adapters.stream.member.command.MemberRegisteredCommand
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
@Lazy
class MemberRegisteredCommandConverter : GenericServicePortConverter<MemberRegisteredCommand, ProfileCreatePort>()