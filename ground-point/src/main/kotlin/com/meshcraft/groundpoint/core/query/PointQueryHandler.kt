package com.meshcraft.groundpoint.core.query

import com.meshcraft.groundpoint.core.entity.Point
import com.meshcraft.groundpoint.core.repository.PointQueryRepository
import org.springframework.stereotype.Service

@Service
class PointQueryHandler(
    private val pointQueryRepository: PointQueryRepository
) {
    fun getPointBy(memberId: Long?): Point? {
        return if (memberId != null) {
            this.pointQueryRepository.findPointByMemberId(memberId)
        } else {
            null
        }
    }
}