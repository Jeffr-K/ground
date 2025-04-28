package com.meshcraft.groundpoint.business

import io.kotest.core.spec.style.BehaviorSpec

class PointPromotionTest : BehaviorSpec({
    given("사용자가 포인트를 프로모션으로 받았을 때") {
        `when`("포인트 프로모션 이벤트가 발생하면") {
            then("포인트가 적립되어야 한다.") {}
            then("포인트 히스토리가 기록되어야 한다.") {}
        }
        `when`("포인트 프로모션 이벤트가 특정 회원에게 주어지면") {
            then("특정 포인트가 적립되어야 한다.") {}
        }
    }

    given("관리자가 프로모션 기간에 포인트를 지급했을 때") {
        `when`("포인트 프로모션 이벤트가 특정 회원에게 주어지면") {
            then("특정 포인트가 적립되어야 한다.") {}
        }
        `when`("포인트 프로모션 이벤트가 전체 회원에게 주어지면") {
            then("전체를 대상으로 포인트가 적립되어야 한다.") {}
        }
        `when`("포인트 프로모션 이벤트가 특정 회원들에게 주어지면") {
            then("특정 회원들에게 포인트가 적립되어야 한다.") {}
        }
        `when`("포인트가 지급되지 말아야 할 대상에게 주어지면") {
            then("포인트가 적립되지 않아야 한다.") {}
        }
    }
})