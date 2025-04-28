package com.meshcraft.groundprofile.core.command

import com.meshcraft.groundcore.converter.Port

data class ProfileCreatePort(val memberId: Long, val gender: String) : Port
