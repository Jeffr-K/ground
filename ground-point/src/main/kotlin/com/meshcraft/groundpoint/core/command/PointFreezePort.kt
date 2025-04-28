package com.meshcraft.groundpoint.core.command

import com.meshcraft.groundcore.converter.Port
import com.meshcraft.groundpoint.core.entity.Point

data class PointFreezePort(val point: Point) : Port
