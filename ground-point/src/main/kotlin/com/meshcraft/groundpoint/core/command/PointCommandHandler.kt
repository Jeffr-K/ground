package com.meshcraft.groundpoint.core.command

import com.meshcraft.groundpoint.core.entity.Point
import com.meshcraft.groundpoint.core.entity.PointHistory
import com.meshcraft.groundpoint.core.values.PointActionType
import com.meshcraft.groundpoint.core.repository.PointCommandRepository
import com.meshcraft.groundpoint.core.repository.PointHistoryRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PointCommandHandler(
    private val pointCommandRepository: PointCommandRepository,
    private val pointHistoryRepository: PointHistoryRepository,
) {

    @Transactional(
        propagation = Propagation.REQUIRED,            // 기존 트랜잭션이 있으면 참여, 없으면 새로 생성
        isolation = Isolation.READ_COMMITTED,          // 커밋된 데이터만 읽기 가능
        timeout = 30,                                  // 30초 후 트랜잭션 타임아웃
        readOnly = false,                              // 읽기 전용 아님 (데이터 변경 가능)
        rollbackFor = [Exception::class],              // 모든 예외에 대해 롤백
        noRollbackFor = [IllegalStateException::class] // IllegalStateException 은 롤백하지 않음
    )
    fun save(port: PointSavePort) {
        if (port.pointActionType != PointActionType.REGISTERED) {
            throw IllegalArgumentException("This Api is used only registered point can be saved")
        }

        val point = Point.create(
            memberId = port.memberId,
            pointActionType = PointActionType.REGISTERED,
            point = 5,
        )

        val savedPoint = this.pointCommandRepository.save(point)

        val pointHistory = PointHistory.create(
            memberId = port.memberId,
            amount = 5,
            balance = 5,
            actionType = PointActionType.REGISTERED,
            description = "earned registered point",
            referenceId = port.referenceId,
            point = savedPoint
        )
        this.pointHistoryRepository.save(pointHistory)
    }

    fun freeze(port: PointFreezePort) {
        val point = port.point.freeze()
        this.pointCommandRepository.save(point)
    }
}