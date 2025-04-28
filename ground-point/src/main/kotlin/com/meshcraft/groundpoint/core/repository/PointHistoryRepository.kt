package com.meshcraft.groundpoint.core.repository

import com.meshcraft.groundpoint.core.entity.PointHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointHistoryRepository : JpaRepository<PointHistory, Long> {

}