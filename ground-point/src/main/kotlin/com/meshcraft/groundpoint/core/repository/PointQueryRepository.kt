package com.meshcraft.groundpoint.core.repository

import com.meshcraft.groundpoint.core.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointQueryRepository : JpaRepository<Point, Long?> {
    fun findPointByMemberId(memberId: Long): Point?
}