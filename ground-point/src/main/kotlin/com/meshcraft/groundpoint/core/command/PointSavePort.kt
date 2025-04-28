package com.meshcraft.groundpoint.core.command

import com.meshcraft.groundcore.converter.Port
import com.meshcraft.groundpoint.core.values.PointActionType

data class PointSavePort(
    val memberId: Long,
    val pointActionType: PointActionType,
    val referenceId: String
) : Port
