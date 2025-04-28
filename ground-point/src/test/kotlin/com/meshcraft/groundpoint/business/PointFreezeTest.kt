package com.meshcraft.groundpoint.business

import io.kotest.core.spec.style.BehaviorSpec

class PointFreezeTest : BehaviorSpec({
    given("사용자가 회원 탈퇴 했을 때") {
        `when`("포인트 동결 요청이 들어오면") {
            then("포인트 동결 로직이 실행되어야 한다.") {}
            then("포인트 동결 이력에 기록되어야 한다.") {}
        }
    }
})