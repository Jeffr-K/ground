package com.meshcraft.groundprofile.core.command

import com.meshcraft.groundcore.converter.Port

data class ProfileEditCommandPort(
    val profileId: Long
) : Port