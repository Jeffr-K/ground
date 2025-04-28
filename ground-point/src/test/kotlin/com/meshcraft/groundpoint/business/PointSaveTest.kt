package com.meshcraft.groundpoint.business

import com.meshcraft.groundpoint.core.command.PointCommandHandler
import com.meshcraft.groundpoint.core.command.PointSavePort
import com.meshcraft.groundpoint.core.entity.Point
import com.meshcraft.groundpoint.core.repository.PointCommandRepository
import com.meshcraft.groundpoint.core.repository.PointHistoryRepository
import com.meshcraft.groundpoint.core.values.PointActionType
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class PointSaveTest : BehaviorSpec({

    val pointCommandRepository = mockk<PointCommandRepository>()
    val pointHistoryRepository = mockk<PointHistoryRepository>()
    val pointCommandHandler = PointCommandHandler(pointCommandRepository, pointHistoryRepository)

    given("사용자가 회원가입 했을 때") {
        val memberId = 1L
        val referenceId = "member-service:member:1:20230428120000"
        val pointSavePort = PointSavePort(
            memberId = memberId,
            pointActionType = PointActionType.REGISTERED,
            referenceId = referenceId
        )

        every { pointCommandRepository.save(any()) } returns Point.create(
            memberId = memberId,
            pointActionType = PointActionType.REGISTERED,
            point = 5
        )

        every { pointHistoryRepository.save(any()) } just awaits

        `when`("포인트 적립 처리가 요청되면") {
            pointCommandHandler.save(pointSavePort)

            then("5 포인트가 저장되어야 한다") {
                verify {
                    pointCommandRepository.save(match {
                        it.memberId == memberId && it.point == 5
                    })
                }
            }

            then("포인트 히스토리가 기록되어야 한다.") {
                verify {
                    pointHistoryRepository.save(match {
                        it.memberId == memberId &&
                                it.amount == 5 &&
                                it.actionType == PointActionType.REGISTERED
                    })
                }
            }
        }
    }

    given("사용자가 이미 회원가입 포인트를 받았을 때") {
        `when`("중복 포인트 적립 요청이 들어오면") {
            then("중복 적립 방지 로직이 실행되어야 한다.") {}
        }
    }

    given("") {
        `when`("") {
            then("") {}
        }
    }
})